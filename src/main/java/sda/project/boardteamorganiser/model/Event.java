package sda.project.boardteamorganiser.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long creatorId;

    @OneToOne
    private EventConfirmation eventConfirmation;
    private boolean isConfirmed;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AppUser appUser;

    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    private Set<Availability> availabilitySet;

    private String description;
    private String place;

    private int minUsers;
    private int maxUsers;

    public Event(EventConfirmation eventConfirmation, boolean isConfirmed, AppUser appUser, Set<Availability> availabilitySet, String description, String place, int minUsers, int maxUsers) {
        this.eventConfirmation = eventConfirmation;
        this.isConfirmed = isConfirmed;
        this.appUser = appUser;
        this.availabilitySet = availabilitySet;
        this.description = description;
        this.place = place;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
    }
}
