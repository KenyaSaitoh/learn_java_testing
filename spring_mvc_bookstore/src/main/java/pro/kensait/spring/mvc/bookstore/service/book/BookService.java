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

    // サービスメソッド： 書籍検索（主キー検索）
    public Book find(Integer bookId) {
        logger.info("[ BookService#find ]");

        Optional<Book> bookOpt = bookRepos.findById(bookId);
        Book book = bookOpt.orElseThrow(() -> new RuntimeException("存在せず"));
        return book;
    }

    // サービスメソッド： 書籍検索（全件検索）
    public List<Book> findAll() {
        logger.info("[ BookService#findAll ]");

        // データベースから全書籍を抽出する
        return bookRepos.findAll();
        // TODO ソートする
    }

    // サービスメソッド： 書籍検索（カテゴリIDとキーワードによる条件検索）
    public List<Book> searchBook(Integer categoryId, String keyword) {
        logger.info("[ BookService#searchBook ]");

        // カテゴリとキーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(toLikeWord(keyword));
    }

    // サービスメソッド： 書籍検索（カテゴリIDによる条件検索）
    public List<Book> searchBook(Integer categoryId) {
        logger.info("[ BookService#searchBook ]");

        // キーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(categoryId);
    }

    // サービスメソッド： 書籍検索（キーワードによる条件検索）
    public List<Book> searchBook(String keyword) {
        logger.info("[ BookService#searchBook ]");

        // キーワードをキーにデータベースから書籍を検索する
        return bookRepos.searchBook(toLikeWord(keyword));
    }

    private String toLikeWord(String keyword) {
        return "%" + keyword + "%";
    }
}