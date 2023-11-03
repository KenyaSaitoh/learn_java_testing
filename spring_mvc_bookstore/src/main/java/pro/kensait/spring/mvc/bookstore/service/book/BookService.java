package pro.kensait.spring.mvc.bookstore.service.book;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(
            BookService.class);

    @Autowired
    private BookRepository bookRepos;

    // ビジネスメソッド
    public Book find(Integer bookId) {
        Optional<Book> bookOpt = bookRepos.findById(bookId);
        Book book = bookOpt.orElseThrow(() -> new RuntimeException("存在せず"));
        return book;
    }

    // ビジネスメソッド（書籍検索処理）
    public List<Book> findAll() {
        // データベースから全書籍を抽出する
        return bookRepos.findAll();
        // TODO ソートする
    }

    // ビジネスメソッド（書籍検索処理）
    public List<Book> searchBook(Integer categoryId, String keyword) {
        // カテゴリとキーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(toLikeWord(keyword));
    }

    // ビジネスメソッド（書籍検索処理）
    public List<Book> searchBook(Integer categoryId) {
        // キーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(categoryId);
    }

    // ビジネスメソッド（書籍検索処理）
    public List<Book> searchBook(String keyword) {
        // キーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(toLikeWord(keyword));
    }

    private String toLikeWord(String keyword) {
        return "%" + keyword + "%";
    }
}