package pro.kensait.spring.mvc.bookstore.web.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.service.book.BookService;

public class BookController {

    @Autowired
    private BookService bookService;

    // アクションメソッド（書籍を検索する） 
    public String search(SearchParam searchParam, Model model) {
        // BookBeanオブジェクトを格納するリストを生成する
        List<Book> bookList;

        // 入力された検索条件から書籍を検索する
        if (searchParam.categoryId() != null) {
            if (searchParam.keyword() != null) {
                bookList = bookService.searchBook(searchParam.categoryId(),
                        searchParam.keyword());
            } else {
                bookList = bookService.searchBook(searchParam.categoryId());
            }
        } else {
            if (searchParam.keyword() != null) {
                bookList = bookService.searchBook(searchParam.keyword());
            } else {
                bookList = bookService.findAll();
            }
        }

        model.addAttribute("bookList", bookList);

        return "";
    }
}