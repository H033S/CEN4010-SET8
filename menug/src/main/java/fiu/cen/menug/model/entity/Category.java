package fiu.cen.menug.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CATEGORY_ID")
    private String id;
    @Column(
            name = "CATEGORY_NAME",
            nullable = false,
            length = 200
    )
    private String name;
}
