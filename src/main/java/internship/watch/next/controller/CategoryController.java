package internship.watch.next.controller;

import internship.watch.next.dto.CategoryDto;
import internship.watch.next.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(category -> new CategoryDto(category.getName()))
                .collect(Collectors.toList());
    }
}
