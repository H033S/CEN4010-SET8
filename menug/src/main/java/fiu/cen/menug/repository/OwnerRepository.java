package fiu.cen.menug.repository;

import fiu.cen.menug.model.Owner;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OwnerRepository extends Repository<Owner, Long> {

    void save(Owner user);
    Optional<Owner> findByUsername(String username);
}
