package com.kendrareynolds.tanititourism.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "things_to_do")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThingToDo extends Listing {


    @Override
    public ListingType getListingType() {
        return ListingType.DO;
    }

    @ManyToMany
    @JoinTable(
            name = "thingtodo_dotype",
            joinColumns = @JoinColumn(name = "thing_to_do_id"),
            inverseJoinColumns = @JoinColumn(name = "do_type_id"))
    @JsonManagedReference
    private Set<DoType> doTypes = new HashSet<>();

    public void add(DoType doType) {
        if(doType != null) {
           if(doTypes == null) {
               doTypes = new HashSet<>();
           }
           doTypes.add(doType);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThingToDo)) return false;
        ThingToDo that = (ThingToDo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "ThingToDo{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", phone='" + super.getPhone() + '\'' +
                ", cost=" + super.getCost() +
                ", region=" + super.getRegion() +
                ", doTypes=" + doTypes +
                '}';
    }



}



