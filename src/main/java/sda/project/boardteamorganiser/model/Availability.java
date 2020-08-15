package sda.project.boardteamorganiser.model;

import javafx.scene.chart.PieChart;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long userId;
//    private Long eventId;

    private Date date;
    private int hours;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AppUser appUser;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Event event;

    public Availability(Date date, int hours, AppUser appUser, Event event) {
        this.date = date;
        this.hours = hours;
        this.appUser = appUser;
        this.event = event;
    }
}
