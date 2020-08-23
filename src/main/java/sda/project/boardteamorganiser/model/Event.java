package sda.project.boardteamorganiser.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long creatorId;

    @OneToOne
    private EventConfirmation eventConfirmation;
    private Boolean Confirmed;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AppUser appUser;

    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Availability> availabilitySet;

    private String title;
    private String description;
    private String place;

    private int minUsers;
    private int maxUsers;

    public Event(EventConfirmation eventConfirmation, boolean Confirmed, AppUser appUser, Set<Availability> availabilitySet, String description, String place, int minUsers, int maxUsers, String title) {
        this.eventConfirmation = eventConfirmation;
        this.Confirmed = Confirmed;
        this.appUser = appUser;
        this.availabilitySet = availabilitySet;
        this.description = description;
        this.place = place;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
        this.title = title;
    }
}
