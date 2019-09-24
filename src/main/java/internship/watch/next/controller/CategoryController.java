package internship.watch.next.controller;

import internship.watch.next.dto.CategoryDto;
import internship.watch.next.model.Category;
import internship.watch.next.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories()
                .stream()
                .map(category -> new CategoryDto(category.getName()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.addCategory(categoryDto.getName());
        return new CategoryDto(category.getName());
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(new CategoryDto(category.get().getName()));
        }
        return ResponseEntity.notFound().build();
    }
}
