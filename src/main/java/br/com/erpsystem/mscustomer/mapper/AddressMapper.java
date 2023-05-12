package br.com.erpsystem.mscustomer.mapper;

import br.com.erpsystem.mscustomer.dto.AddressDTO;
import br.com.erpsystem.mscustomer.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address addressDtoToAddress(AddressDTO addressDTO);
    AddressDTO addressToAddressDTO(Address address);

}