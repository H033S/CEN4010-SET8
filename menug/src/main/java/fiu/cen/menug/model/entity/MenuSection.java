package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "menu_section")
public class MenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MENU_SECTION_ID")
    private String id;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "MENU_SECTION_CATEGORY_ID",
            referencedColumnName = "CATEGORY_ID"
    )
    private Category category;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem) {

        if (Objects.isNull(menuItems)) {
            menuItems = new HashSet<>();
        }

        menuItems.add(menuItem);
    }
}
