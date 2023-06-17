package com.kendrareynolds.tanititourism.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "do_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_name", unique = true)
    private String typeName;

    @ManyToMany(mappedBy = "doTypes")
    @JsonBackReference
    private Set<ThingToDo> thingsToDo = new HashSet<>();

    public void add(ThingToDo thingToDo) {
        if (thingToDo != null) {
            if (thingsToDo == null) {
                thingsToDo = new HashSet<>();
            }
            thingsToDo.add(thingToDo);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoType)) return false;
        DoType that = (DoType) o;
        return Objects.equals(id, that.id);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
