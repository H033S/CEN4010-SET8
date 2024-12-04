package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "menu_item")
public class MenuItem {

    public MenuItem(){}
    public MenuItem(
            String description,
            BigDecimal price
    ){
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MENU_ITEM_ID")
    private String id;
    @Column(
            name = "MENU_ITEM_DESCRIPTION",
            nullable = false,
            length = 200
    )
    private String description;
    @Column(
            name = "MENU_ITEM_PRICE",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal price;
    @Pattern(regexp = "^(https?:\\/\\/.*\\.(?:png|jpg|jpeg|gif|webp))$")
    @Column(name = "MENU_ITEM_IMAGE_URL")
    private String imageUrl;
}
