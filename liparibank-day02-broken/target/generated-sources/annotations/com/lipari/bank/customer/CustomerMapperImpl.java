package com.lipari.bank.customer;

import com.lipari.bank.customer.dto.CustomerCreateRequest;
import com.lipari.bank.customer.dto.CustomerResponse;
import com.lipari.bank.customer.dto.CustomerUpdateRequest;
import com.lipari.bank.customer.model.Customer;
import com.lipari.bank.customer.model.CustomerStatus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-08T23:16:01+0200",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.11 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFiscalCode( request.fiscalCode() );
        customer.setFirstName( request.firstName() );
        customer.setLastName( request.lastName() );
        customer.setEmail( request.email() );
        customer.setPhone( request.phone() );

        customer.setStatus( CustomerStatus.ACTIVE );

        return customer;
    }

    @Override
    public CustomerResponse toResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        customerResponse.id( customer.getId() );
        customerResponse.fiscalCode( customer.getFiscalCode() );
        customerResponse.firstName( customer.getFirstName() );
        customerResponse.lastName( customer.getLastName() );
        customerResponse.email( customer.getEmail() );
        customerResponse.phone( customer.getPhone() );
        customerResponse.status( customer.getStatus() );

        return customerResponse.build();
    }

    @Override
    public void updateEntityFromRequest(CustomerUpdateRequest request, Customer customer) {
        if ( request == null ) {
            return;
        }

        customer.setEmail( request.email() );
        customer.setPhone( request.phone() );
    }
}
