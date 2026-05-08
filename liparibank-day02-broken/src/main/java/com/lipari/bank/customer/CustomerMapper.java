package com.lipari.bank.customer;

import com.lipari.bank.customer.dto.CustomerCreateRequest;
import com.lipari.bank.customer.dto.CustomerResponse;
import com.lipari.bank.customer.dto.CustomerUpdateRequest;
import com.lipari.bank.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE")
    Customer toEntity(CustomerCreateRequest request);

    CustomerResponse toResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fiscalCode", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromRequest(CustomerUpdateRequest request, @MappingTarget Customer customer);
}
