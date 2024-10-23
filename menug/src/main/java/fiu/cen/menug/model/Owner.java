package fiu.cen.menug.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OWNER")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OWNER_FIRST_NAME", length = 50, nullable = false)
    private final String firstName;
    @Column(name = "OWNER_LAST_NAME", length = 50, nullable = false)
    private final String lastName;
    @Column(name = "OWNER_OWNERNAME", length = 30, nullable = false, unique = true)
    private final String username;
    @Column(name = "OWNER_PASSWORD", length = 200, nullable = false)
    private final String password;

    public Owner(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
