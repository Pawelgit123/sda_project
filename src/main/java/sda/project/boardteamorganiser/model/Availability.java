package sda.project.boardteamorganiser.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long userId;
//    private Long eventId;

    private LocalDate date;
    private int hours;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AppUser appUser;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Event event;

    public Availability(LocalDate date, int hours) {
        this.date = date;
        this.hours = hours;
    }
}
