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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "thingtodo_dotype",
            joinColumns = @JoinColumn(name = "thing_to_do_id"),
            inverseJoinColumns = @JoinColumn(name = "do_type_id"))
    @JsonManagedReference
    private Set<DoType> doTypes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
     if (this == o) return true;
     if(!(o instanceof ThingToDo)) return false;
     ThingToDo that = (ThingToDo) o;
     return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
