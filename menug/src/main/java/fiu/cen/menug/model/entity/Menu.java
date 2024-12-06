package fiu.cen.menug.model.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

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
    @Column(name = "MENU_NAME")
    private String name;
    @Column(name = "MENU_CREATION_DATE")
    private LocalDate creationDate;
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
