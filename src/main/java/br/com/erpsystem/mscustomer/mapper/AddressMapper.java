package br.com.erpsystem.mscustomer.mapper;

import br.com.erpsystem.mscustomer.dto.AddressDTO;
import br.com.erpsystem.mscustomer.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address addressDtoToAddress(AddressDTO addressDTO);
    AddressDTO addressToAddressDTO(Address address);

    List<Address> addressesDtosToAddresses(List<AddressDTO> addressesDTO);

    List<AddressDTO> addressesToAddressesDto(List<Address> addresses);

    default void updateAddressFromDto(List<AddressDTO> addressDTO, @MappingTarget List<Address> address){
        if(addressDTO == null){
            return;
        }

        Address firstAddress = address.get(0);
        AddressDTO firstAddressDTO = addressDTO.get(0);

        if(firstAddressDTO.getAddress() != null){
            firstAddress.setAddress(firstAddressDTO.getAddress());
        }

        if(firstAddressDTO.getCity() != null){
            firstAddress.setCity(firstAddressDTO.getCity());
        }

        if(firstAddressDTO.getNumber() != null){
            firstAddress.setNumber(firstAddressDTO.getNumber());
        }

        if(firstAddressDTO.getState() != null){
            firstAddress.setState(firstAddressDTO.getNumber());
        }

        if(firstAddressDTO.getNeighborhood() != null){
            firstAddress.setNeighborhood(firstAddressDTO.getNeighborhood());
        }

        if(firstAddressDTO.getPostalCode() != null){
            firstAddress.setPostalCode(firstAddressDTO.getPostalCode());
        }
    }



}