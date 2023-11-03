package pro.kensait.spring.mvc.bookstore.service.category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pro.kensait.spring.mvc.bookstore.entity.Category;
import pro.kensait.spring.mvc.bookstore.repository.CategoryRepository;

public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(
            CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepos;
    
    public List<Category> getCategoryList() {
        return categoryRepos.findAll();
    }
}