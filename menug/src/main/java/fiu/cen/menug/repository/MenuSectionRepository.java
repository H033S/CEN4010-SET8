package fiu.cen.menug.repository;

import fiu.cen.menug.model.entity.MenuSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSectionRepository extends JpaRepository<MenuSection, String> {}
