package br.com.erpsystem.mscustomer.mapper;

import br.com.erpsystem.mscustomer.dto.CustomerDTO;
import br.com.erpsystem.mscustomer.dto.http.request.CustomerUpdateRequestDTO;
import br.com.erpsystem.mscustomer.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, DateMapper.class})
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);

   // Customer customerUpdateToCustomer (CustomerUpdateRequestDTO customerUpdateRequestDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerUpdateRequestDTO customerUpdateRequestDTO, @MappingTarget Customer customer);



}
