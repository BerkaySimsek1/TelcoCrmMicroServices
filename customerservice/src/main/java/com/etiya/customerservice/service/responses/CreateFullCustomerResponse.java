package com.etiya.customerservice.service.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFullCustomerResponse {
    private String customerId;
    private String customerNumber;
    private List<CreatedAddressMini> addresses;
    private List<CreatedContactMini> contactMediums;

    public static class CreatedAddressMini {
        private int id;
        private boolean isDefault;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public void setDefault(boolean aDefault) {
            isDefault = aDefault;
        }
    }
    public static class CreatedContactMini {
        private int id;
        private String type;
        private boolean isPrimary;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isPrimary() {
            return isPrimary;
        }

        public void setPrimary(boolean primary) {
            isPrimary = primary;
        }
    }

    // getters/setters
}
