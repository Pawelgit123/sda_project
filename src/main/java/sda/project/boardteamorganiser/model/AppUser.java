package sda.project.boardteamorganiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nick;

    private String login;
    private String password;
    private String email;

    private Boolean isAcitve;

    @OneToMany (mappedBy = "appUser")
    private Set<Availability> availabilitySet;

    @OneToMany(mappedBy = "appUser")
    private Set<Event> eventSet;

    public AppUser(String nick, String login, String password, String email, Boolean isAcitve) {
        this.nick = nick;
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAcitve = isAcitve;
    }
}
