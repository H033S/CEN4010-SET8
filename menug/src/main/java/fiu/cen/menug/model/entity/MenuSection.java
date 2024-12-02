package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menu_section")
public class MenuSection {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MENU_SECTION_ID")
    private String id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "MENU_SECTION_CATEGORY_ID",
            referencedColumnName = "CATEGORY_ID"
    )
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "MENU_SECTION_MENU_ITEM_ID",
            referencedColumnName = "MENU_ITEM_ID"
    )
    private MenuItem menuItem;
    @ManyToOne
    @JoinColumn(
            name = "MENU_SECTION_MENU_ID",
            nullable = false
    )
    private Menu menu;
}
