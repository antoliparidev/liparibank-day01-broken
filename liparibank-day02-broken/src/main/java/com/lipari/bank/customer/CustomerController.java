package com.lipari.bank.customer;

import com.lipari.bank.customer.dto.CustomerCreateRequest;
import com.lipari.bank.customer.dto.CustomerResponse;
import com.lipari.bank.customer.dto.CustomerUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customers", description = "API per la gestione dei clienti LipariBank")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @Operation(summary = "Lista clienti", description = "Restituisce la lista di tutti i clienti.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista recuperata con successo")
    })
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Dettaglio cliente", description = "Recupera i dettagli di un cliente tramite ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente trovato"),
            @ApiResponse(responseCode = "404", description = "Cliente non trovato")
    })
    public ResponseEntity<CustomerResponse> findById(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creazione cliente", description = "Crea un nuovo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati non validi")
    })
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerCreateRequest request
    ) {
        CustomerResponse created = customerService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiornamento cliente")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request
    ) {
        return ResponseEntity.ok(customerService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminazione cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente eliminato"),
            @ApiResponse(responseCode = "404", description = "Cliente non trovato")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
