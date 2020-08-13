package sda.project.boardteamorganiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public AppUser(String nick, String login, String password, String email, Boolean isAcitve) {
        this.nick = nick;
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAcitve = isAcitve;
    }
}
