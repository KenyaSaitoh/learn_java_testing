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

    // アクションメソッド：BookSelectPageにフォワードする
    @GetMapping("/toSelect")
    public String toSelect(Model model) {
        logger.info("[ BookController#toSelect ]");

        // 書籍エンティティのリストを取得し、モデルに追加する
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);

        // BookSelectPageにフォワードする
        return "BookSelectPage";
    }

    // アクションメソッド：BookSearchPageにフォワードする
    @GetMapping("/toSearch")
    public String toSearch(@ModelAttribute("searchParam") SearchParam searchParam,
            Model model) {
        logger.info("[ BookController#toSearch ]");

        // カテゴリを表すマップ（セレクトボックス用）を取得し、モデルに追加する
        Map<String, Integer> categoryMap = new HashMap<>();
        categoryMap.put("", null);
        categoryMap.putAll(categoryService.getCategoryMap());
        model.addAttribute("categoryMap", categoryMap);

        // BookSearchPageにフォワードする
        return "BookSearchPage";
    }

    // アクションメソッド：書籍を検索する（静的なクエリを使用する）
    @GetMapping("/search")
    public String search(SearchParam searchParam, Model model) {
        logger.info("[ BookController#search ]");

        // サービスを呼び出し、入力された検索条件から書籍エンティティを検索する
        List<Book> bookList;
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
                bookList = bookService.getAll();
            }
        }

        // 取得した書籍エンティティのリストを、モデルに追加する
        model.addAttribute("bookList", bookList);

        // BookSearchPageにフォワードする
        return "BookSelectPage";
    }

    // アクションメソッド：書籍を検索する（動的なクエリを使用する）
    @GetMapping("/search2")
    public String search2(SearchParam searchParam, Model model) {
        logger.info("[ BookController#search2 ]");

        // 入力された検索条件から動的にクエリを構築し、書籍を検索する
        List<Book> bookList = bookService.searchBookWithCriteria(
                searchParam.categoryId(), searchParam.keyword());

        // 取得した書籍エンティティのリストを、モデルに追加する
        model.addAttribute("bookList", bookList);

        // BookSearchPageにフォワードする
        return "BookSelectPage";
    }
}