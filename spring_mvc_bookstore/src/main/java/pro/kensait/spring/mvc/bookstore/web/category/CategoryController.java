package pro.kensait.spring.mvc.bookstore.web.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pro.kensait.spring.mvc.bookstore.entity.Category;
import pro.kensait.spring.mvc.bookstore.service.category.CategoryService;

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }
}