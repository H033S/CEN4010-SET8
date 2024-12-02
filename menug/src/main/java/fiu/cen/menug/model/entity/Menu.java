package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * Menu
 */
@Entity
@Data
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MENU_ID")
    private String id;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "menu"
    )
    private Set<MenuSection> menuSections;
    @ManyToOne
    @JoinColumn(
            name = "MENU_APP_USER_ID",
            nullable = false
    )
    private User user;
}
