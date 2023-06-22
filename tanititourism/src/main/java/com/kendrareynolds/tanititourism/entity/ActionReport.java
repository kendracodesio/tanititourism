package com.kendrareynolds.tanititourism.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "action_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionReport {



    public enum Action {
        CREATE, UPDATE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;

    @Column(name = "timestamp", nullable = false)
    private OffsetDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "thing_to_do_id")
    private ThingToDo thingToDo;

    @ManyToOne
    @JoinColumn (name = "places_to_stay_id")
    private PlaceToStay placesToStay;

    @ManyToOne
    @JoinColumn (name = "restaurants_and_nightlife_id")
    private RestaurantsAndNightlife restaurantsAndNightlife;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionReport)) return false;
        ActionReport that = (ActionReport) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}


