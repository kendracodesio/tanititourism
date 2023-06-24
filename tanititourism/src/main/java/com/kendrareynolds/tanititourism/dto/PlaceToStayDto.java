package com.kendrareynolds.tanititourism.dto;

import com.kendrareynolds.tanititourism.entity.Cost;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class PlaceToStayDto {

    @NotBlank(message = "Listing name must not be blank")
    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    private String name;

    @NotBlank(message= "Description cannot be blank")
    @Size(min = 50, max = 500, message = "Description should be between 50 and 500 characters")
    private String description;

    @Pattern(regexp="^\\+\\d{3}\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}$", message="Phone number should be numeric and follow this format: +XXX XX XX XX XX")
    private String phone;

    @NotNull(message = "Must select an option")
    private Cost cost;

    @URL(message = "Invalid image URL")
    @Pattern(regexp = ".*\\.(jpg|png|jpeg|gif)$", message="Image URL must end with a valid image extension (.jpg, .png, .jpeg, .gif)")
    private String imageUrl;

    @NotBlank(message="Image alt text cannot be blank")
    @Size(max = 100, message="Image alt text cannot be longer than 100 characters")
    private String imageAltText;

    @NotNull(message = "Must select a region")
    private Long regionId;

    @NotNull(message = "Must select a type")
    private Long stayTypeId;


}
