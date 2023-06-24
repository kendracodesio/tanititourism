package com.kendrareynolds.tanititourism.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "action_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionReport {
    public ActionReport(Action action, LocalDateTime timestamp, ThingToDo thingToDo, User user) {
        this.action = action;
        this.timestamp = timestamp;
        this.thingToDo = thingToDo;
        this.user = user;
    }

    public ActionReport(Action action, LocalDateTime timestamp, PlaceToStay placeToStay, User user) {
        this.action = action;
        this.timestamp = timestamp;
        this.placeToStay = placeToStay;
        this.user = user;
    }

    public ActionReport(Action action, LocalDateTime timestamp, RestaurantsAndNightlife restaurantsAndNightlife, User user) {
        this.action = action;
        this.timestamp = timestamp;
        this.restaurantsAndNightlife = restaurantsAndNightlife;
        this.user = user;
    }

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
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "thing_to_do_id")
    private ThingToDo thingToDo;

    @ManyToOne
    @JoinColumn (name = "places_to_stay_id")
    private PlaceToStay placeToStay;

    @ManyToOne
    @JoinColumn (name = "restaurants_and_nightlife_id")
    private RestaurantsAndNightlife restaurantsAndNightlife;

    @ManyToOne
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


