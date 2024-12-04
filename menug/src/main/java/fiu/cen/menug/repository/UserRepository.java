package fiu.cen.menug.repository;

import fiu.cen.menug.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = {
            "menuList.menuSections.menuItems",
            "menuList.menuSections.category"
    })
    Optional<User> findByUsername(String username);
}
