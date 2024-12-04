package fiu.cen.menug.service;

import fiu.cen.menug.model.entity.Category;
import fiu.cen.menug.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Optional<Category> findById(String categoryId){
        return categoryRepository.findById(categoryId);
    }

    public boolean existById(String categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public void deleteById(String categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public void save(Category category){
        categoryRepository.save(category);
    }
}
