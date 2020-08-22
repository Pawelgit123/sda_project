package sda.project.boardteamorganiser.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String email;

    private Boolean active;

    @OneToMany (mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private Set<Availability> availabilitySet;

    @OneToMany(mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private Set<Event> eventSet;

    public AppUser(String login, String password, String email, Boolean isAcitve) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = isAcitve;
    }
}

