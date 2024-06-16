package com.demo.customer.controller;

import com.demo.customer.dto.ApiErrorDto;
import com.demo.customer.dto.CustomerRequest;
import com.demo.customer.dto.CustomerResponse;
import com.demo.customer.model.Customer;
import com.demo.customer.model.CustomerSearchRequest;
import com.demo.customer.service.CustomerService;
import com.demo.customer.service.MapperService;
import com.demo.customer.util.EndPointConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for managing Customer resource.
 * <p>
 * This class has controller methods to expose various APIs for the customer resource.
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(description = "Customer Controller", name = "customer-controller")
public class CustomerController {
    private final CustomerService service;
    private final MapperService mapperService;

    /**
     * Creates a new customer.
     *
     * @param request CustomerRequest
     * @return ResponseEntity<CustomerResponse>
     */
    @Operation(description = "Create customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Failed with message",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorDto.class))
                    })
    })
    @PostMapping(EndPointConstants.CUSTOMERS)
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest request) {
        Customer customer = mapperService.converToCustomer(request);
        customer = service.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperService.converToCustomerResponse(customer));
    }


    /**
     * Fetches a list of customer based on the input search criteria (query param).
     *
     * @param firstName String
     * @param lastName  String
     * @param city      String
     * @param state     String
     * @param limit     Integer
     * @param offset    Integer
     * @return ResponseEntity<List < CustomerResponse>>
     */

    @Operation(description = "Fetch customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Failed with message",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiErrorDto.class))
                    })
    })
    @GetMapping(EndPointConstants.CUSTOMERS)
    public ResponseEntity<List<CustomerResponse>> fetchCustomers(@RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "state", required = false) String state, @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit, @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        CustomerSearchRequest request = new CustomerSearchRequest(firstName, lastName, city, state, limit, offset);
        List<Customer> customerList = service.fetchCustomers(request);
        return ResponseEntity.status(HttpStatus.OK).body(mapperService.converToCustomerResponseList(customerList));
    }
}
