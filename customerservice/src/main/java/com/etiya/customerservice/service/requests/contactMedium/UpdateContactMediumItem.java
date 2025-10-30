package com.etiya.customerservice.service.requests.contactMedium;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactMediumItem {

    @NotNull(message = "ContactMedium id cannot be null")
    private Integer id;

    @NotBlank(message = Messages.ContactMediumTypeNotBlank)
    @Pattern(
            regexp = "^(?i)(email|mobile_phone|home_phone|fax)$",
            message = Messages.ContactMediumTypePattern
    )
    private String type;

    @NotBlank(message = Messages.ContactMediumValueNotBlank)
    @Length(max = 150, message = Messages.ContactMediumValueLength)
    private String value;

    @NotNull(message = Messages.ContactMediumIsPrimaryNotNull)
    private boolean isPrimary;
}



