package com.etiya.customerservice.service.concretes;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.events.CreateCustomerEvent;
import com.etiya.common.events.DeleteCustomerEvent;
import com.etiya.common.events.SoftDeleteCustomerEvent;
import com.etiya.common.events.UpdateCustomerEvent;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualCustomer.*;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.DeleteCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.SoftDeleteCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.UpdateCustomerProducer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final CreateCustomerProducer createCustomerProducer;
    private final UpdateCustomerProducer updateCustomerProducer;

    private final DeleteCustomerProducer deleteCustomerProducer;
    private final SoftDeleteCustomerProducer softDeleteCustomerProducer;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules individualCustomerBusinessRules, CreateCustomerProducer createCustomerProducer, UpdateCustomerProducer updateCustomerProducer, DeleteCustomerProducer deleteCustomerProducer, SoftDeleteCustomerProducer softDeleteCustomerProducer) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerBusinessRules =  individualCustomerBusinessRules;
        this.createCustomerProducer = createCustomerProducer;
        this.updateCustomerProducer = updateCustomerProducer;
        this.deleteCustomerProducer = deleteCustomerProducer;
        this.softDeleteCustomerProducer = softDeleteCustomerProducer;
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        individualCustomerBusinessRules.checkIfIndividualCustomerExistsByIdentityNumber(request.getNationalId());

        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        CreateCustomerEvent event =
                new CreateCustomerEvent(createdIndividualCustomer.getId().toString(),
                        createdIndividualCustomer.getCustomerNumber(),
                        createdIndividualCustomer.getFirstName(),
                        createdIndividualCustomer.getLastName(),
                        createdIndividualCustomer.getNationalId(),
                        createdIndividualCustomer.getDateOfBirth().toString(),
                        createdIndividualCustomer.getMotherName(),
                        createdIndividualCustomer.getFatherName(),
                        createdIndividualCustomer.getGender());

        createCustomerProducer.produceCustomerCreated(event);

        CreatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);

        return response;
    }



    @Override
    public List<GetListIndividualCustomerResponse> getList() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();

        List<GetListIndividualCustomerResponse> responses = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponsesFromIndividualCustomers(individualCustomers);

        return responses;
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UUID id, UpdateIndividualCustomerRequest request) {
        IndividualCustomer existingCustomer = individualCustomerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Customer not found with id: " + id));

        // Sadece request’te DOLU gelen alanlar existingCustomer’a yazılır;
        // null olanlar olduğu gibi kalır.
        IndividualCustomerMapper.INSTANCE.updateIndividualCustomerFromRequest(request, existingCustomer);

        // İstersen değişmemesi gereken alanları burada kilitle:
        // existingCustomer.setNationalId(existingCustomer.getNationalId()); // örnek

        IndividualCustomer saved = individualCustomerRepository.save(existingCustomer);

        // Yeni eklenen kısım: Olayı oluştur ve gönder
        UpdateCustomerEvent event =
                new UpdateCustomerEvent(
                        saved.getId().toString(), // UUID to String
                        saved.getCustomerNumber(),
                        saved.getFirstName(),
                        saved.getLastName(),
                        saved.getNationalId(),
                        saved.getDateOfBirth().toString(),
                        saved.getMotherName(),
                        saved.getFatherName(),
                        saved.getGender());


        updateCustomerProducer.produceCustomerUpdated(event); // Producer sınıfında kafka topic ile iletişim kuran metoda yolla


        return IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(saved);
    }


    @Override
    public List<GetListIndividualCustomerWithAddressResponse> findAllWithAddresses() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAllWithAddresses();

        List<GetListIndividualCustomerWithAddressResponse> responses = IndividualCustomerMapper.INSTANCE.getListIndividualCustomersWithAddressResponsesFromIndividualCustomers(individualCustomers);

        return responses;
    }

    @Override
    public List<GetIndividualCustomerResponse> getByLastName(String lastName) {
        List<IndividualCustomer> individualCustomer = individualCustomerRepository.findByLastNameIgnoreCase(lastName);
        List<GetIndividualCustomerResponse> response =
                IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomer(individualCustomer);
        return response;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix) {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findByFirstNameStartingPrefix(prefix);

        List<GetListIndividualCustomerResponse> response = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponsesFromIndividualCustomers(individualCustomers);
        return response;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern) {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findByCustomerNumberPattern(pattern);

        List<GetListIndividualCustomerResponse> response = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponsesFromIndividualCustomers(individualCustomers);
        return response;
    }

    @Override
    public GetIndividualCustomerResponse getById(UUID id) {
        individualCustomerBusinessRules.checkIfCustomerId(id);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).get();
        GetIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomer(individualCustomer);
        return response;
    }

    @Override
    public void delete(UUID id) {
        individualCustomerBusinessRules.checkIfCustomerId(id);
        // !! ÖNEMLİ: Müşteriyi silmeden önce ilişkili başka veriler varsa (aktif fatura hesabı)
        // bunlarla ilgili business rule'lar eklenmelidir. Şimdilik sadece ID kontrolü yapıyorum.
        IndividualCustomer customerToDelete = individualCustomerRepository.findById(id).get();

        DeleteCustomerEvent event = new DeleteCustomerEvent(id.toString());
        deleteCustomerProducer.produceCustomerDeleted(event); // Event'i gönder

        individualCustomerRepository.delete(customerToDelete);
    }

    @Override
    public void softDelete(UUID id) {
        individualCustomerBusinessRules.checkIfCustomerId(id); // Müşteri var mı kontrolü

        IndividualCustomer customerToSoftDelete = individualCustomerRepository.findById(id).get();

        customerToSoftDelete.setDeletedDate(LocalDateTime.now()); // Silinme tarihini set et
        IndividualCustomer savedCustomer = individualCustomerRepository.save(customerToSoftDelete); // Kaydet

        SoftDeleteCustomerEvent event = new SoftDeleteCustomerEvent(
                savedCustomer.getId().toString(),
                savedCustomer.getDeletedDate().toString()
        );
        softDeleteCustomerProducer.produceCustomerSoftDeleted(event); // Event'i gönder
    }

    @Override
    public boolean existsByNationalId(String nationalId) {
        return individualCustomerRepository.existsByNationalId(nationalId);
    }


}
