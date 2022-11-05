package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Category;
import fr.hoenheimsports.gestionclub.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    final private CategoryRepository categoryRepository;

    @Override
    public Category createOrUpdate(String shortName, String longName) {
        Optional<Category> optionalCategory = this.categoryRepository.findByShortName(shortName);
        Category cat = optionalCategory.orElseGet(() -> this.categoryCreate(shortName));
        cat.setLongName(longName);
        return this.categoryRepository.save(cat);
    }

    private Category categoryCreate(String shortName) {
        Category newCategory = new Category();
        newCategory.setShortName(shortName);
        return newCategory;
    }
}
