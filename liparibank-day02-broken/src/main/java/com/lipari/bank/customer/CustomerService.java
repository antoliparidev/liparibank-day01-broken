package com.lipari.bank.customer;

import com.lipari.bank.customer.dto.CustomerCreateRequest;
import com.lipari.bank.customer.dto.CustomerResponse;
import com.lipari.bank.customer.dto.CustomerUpdateRequest;
import com.lipari.bank.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {
        log.debug("Recupero lista clienti");
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    public CustomerResponse findById(Long id) {
        log.debug("Recupero cliente con id: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato con id: " + id));
        return customerMapper.toResponse(customer);
    }

    public CustomerResponse create(CustomerCreateRequest request) {
        log.info("Creazione nuovo cliente: {}", request.fiscalCode());
        Customer customer = customerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        log.info("Cliente creato con id: {}", saved.getId());
        return customerMapper.toResponse(saved);
    }

    public CustomerResponse update(Long id, CustomerUpdateRequest request) {
        log.info("Aggiornamento cliente con id: {}", id);
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato con id: " + id));
        customerMapper.updateEntityFromRequest(request, customer);
        return customerMapper.toResponse(customer);
    }

    public void delete(Long id) {
        log.info("Eliminazione cliente con id: {}", id);
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Cliente non trovato con id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
