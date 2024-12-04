package fiu.cen.menug.service;

import fiu.cen.menug.model.entity.MenuSection;
import fiu.cen.menug.repository.MenuSectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuSectionService {

    private final MenuSectionRepository menuSectionRepository;

    public MenuSectionService(MenuSectionRepository menuSectionRepository) {
        this.menuSectionRepository = menuSectionRepository;
    }

    public Optional<MenuSection> findMenuById(String sectionId) {
        return menuSectionRepository.findById(sectionId);
    }

    public boolean existById(String sectionId) {
        return menuSectionRepository.existsById(sectionId);
    }

    public void deleteById(String sectionId){
         menuSectionRepository.deleteById(sectionId);
    }
}
