package br.com.erpsystem.mscustomer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private UUID id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("number")
    private String number;
    @JsonProperty("neighborhood")
    private String neighborhood;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;


}
