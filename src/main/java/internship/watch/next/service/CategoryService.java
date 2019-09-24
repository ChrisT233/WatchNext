package internship.watch.next.service;

import internship.watch.next.helper.Helper;
import internship.watch.next.model.Category;
import internship.watch.next.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Helper helper;

    public List<Category> getAllCategories() {

        return (List<Category>) categoryRepository.findAll();
    }

    public Category addCategory(String name) {
        if (helper.hasAccess()) {
            Category category = categoryRepository.findByName(name);
            if (category == null) {
                category = new Category(name);
                categoryRepository.save(category);
            }
            return category;
        } else {
            throw new RuntimeException("You do not have access.");
        }
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
