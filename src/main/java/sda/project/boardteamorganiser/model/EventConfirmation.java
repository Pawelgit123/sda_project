package sda.project.boardteamorganiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private int hours;

    @OneToOne
    private Event event;

    public EventConfirmation(Date date, int hours, Event event) {
        this.date = date;
        this.hours = hours;
        this.event = event;
    }
}
