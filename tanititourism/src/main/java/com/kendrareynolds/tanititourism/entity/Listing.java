package com.kendrareynolds.tanititourism.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.OffsetDateTime;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Listing {

    public enum ListingType {
        DO, STAY, DINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


//    @NotBlank(message = "Listing name must not be blank")
//    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    @Column(name = "name", unique = true)
    private String name;

//    @NotBlank
//    @Size(min = 100, max = 400, message = "Description should be between 50 and 350 characters")
    @Column(name = "description", length = 1000)
    private String description;

//    @Pattern(regexp="^\\+\\d{3}\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}$", message="Phone number should be numeric and follow this format: +XXX XX XX XX XX")
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "cost")
    private Cost cost;

//    @URL(message = "Invalid image URL")
//    @Pattern(regexp = ".*\\.(jpg|png|jpeg|gif)$", message="Image URL must end with a valid image extension (.jpg, .png, .jpeg, .gif)")
    @Column(name = "image_url")
    private String imageUrl;

//    @NotBlank
//    @Size(max = 100, message="Image alt text cannot be longer than 100 characters")
    @Column(name = "image_alt_text")
    private String imageAltText;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;



    public abstract ListingType getListingType();

    @Override
    public String toString() {
        return "Listing{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", cost=" + cost +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageAltText='" + imageAltText + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", region=" + region +
                '}';
    }
}


