package pro.kensait.spring.mvc.bookstore.service.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.kensait.spring.mvc.bookstore.entity.Category;
import pro.kensait.spring.mvc.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(
            CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepos;

    // サービスメソッド：
    public Map<String, Integer> getCategoryMap() {
        logger.info("[ CategoryService#getCategoryMap ]");

        List<Category> categoryList = categoryRepos.findAll();
        Map<String, Integer> categoryMap = new HashMap<String, Integer>();
        for (Category category : categoryList) {
            categoryMap.put(category.getCategoryName(), category.getCategoryId());
        }
        return categoryMap;
    }
}