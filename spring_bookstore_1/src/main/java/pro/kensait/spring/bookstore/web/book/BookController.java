package pro.kensait.spring.bookstore.web.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pro.kensait.spring.bookstore.entity.Book;
import pro.kensait.spring.bookstore.service.book.BookService;
import pro.kensait.spring.bookstore.service.category.CategoryService;

@Controller
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(
            BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/toSelect")
    public String toSelect(Model model) {
        logger.info("[ BookController#toSelect ]");

        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "BookSelectPage";
    }

    @GetMapping("/toSearch")
    public String toSearch(@ModelAttribute("searchParam") SearchParam searchParam,
            Model model) {
        logger.info("[ BookController#toSearch ]");

        Map<String, Integer> categoryMap = new HashMap<>();
        categoryMap.put("", null);
        categoryMap.putAll(categoryService.getCategoryMap());
        model.addAttribute("categoryMap", categoryMap);
        return "BookSearchPage";
    }

    // アクションメソッド（書籍を検索する） 
    @GetMapping("/search")
    public String search(SearchParam searchParam, Model model) {
        logger.info("[ BookController#toSearch ]");

        // BookBeanオブジェクトを格納するリストを生成する
        List<Book> bookList;

        // 入力された検索条件から書籍を検索する
        if (searchParam.categoryId() != null) {
            if (searchParam.keyword() != null && ! searchParam.keyword().isEmpty()) {
                bookList = bookService.searchBook(searchParam.categoryId(),
                        searchParam.keyword());
            } else {
                bookList = bookService.searchBook(searchParam.categoryId());
            }
        } else {
            if (searchParam.keyword() != null && ! searchParam.keyword().isEmpty()) {
                bookList = bookService.searchBook(searchParam.keyword());
            } else {
                bookList = bookService.findAll();
            }
        }

        model.addAttribute("bookList", bookList);

        return "BookSelectPage";
    }
}