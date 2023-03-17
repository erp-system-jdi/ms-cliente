package br.com.erpsystem.mscliente.mapper;

import br.com.erpsystem.mscliente.dto.AddressDTO;
import br.com.erpsystem.mscliente.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address addressDtoToAddress(AddressDTO addressDTO);

    AddressDTO addressToAddressDTO(Address address);

}
