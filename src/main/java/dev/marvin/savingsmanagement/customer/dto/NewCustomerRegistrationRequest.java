package dev.marvin.savingsmanagement.customer.dto;

public record NewCustomerRegistrationRequest(String name, String email, String mobile, String governmentId,
                                             String address) {
}
