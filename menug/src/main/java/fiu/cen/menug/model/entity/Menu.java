package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
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
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<MenuSection> menuSections;

    public void addMenuSection(MenuSection menuSection) {

        if(Objects.isNull(menuSections)){
            menuSections = new HashSet<>();
        }

        menuSections.add(menuSection);
    }

}
