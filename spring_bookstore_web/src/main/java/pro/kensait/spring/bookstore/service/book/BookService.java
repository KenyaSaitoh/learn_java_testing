package pro.kensait.spring.bookstore.service.book;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pro.kensait.spring.bookstore.entity.Book;
import pro.kensait.spring.bookstore.repos.BookRepository;

@Service
@Transactional
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(
            BookService.class);

    @Autowired
    private BookRepository bookRepos;

    @PersistenceContext
    private EntityManager entityManager;

    // サービスメソッド： 書籍検索（主キー検索）
    public Book getBook(Integer bookId) {
        logger.info("[ BookService#getBook ]");

        Optional<Book> bookOpt = bookRepos.findById(bookId);
        Book book = bookOpt.orElseThrow(() -> new RuntimeException("存在せず"));
        return book;
    }

    // サービスメソッド： 書籍検索（全件検索）
    public List<Book> getAll() {
        logger.info("[ BookService#getAll ]");

        // データベースから全書籍を抽出する
        return bookRepos.findAll();
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

    // サービスメソッド： 書籍検索（動的クエリの構築）
    public List<Book> searchBookWithCriteria(Integer categoryId, String keyword) {
        logger.info("[ BookService#searchBookWithCriteria ]");

        // CriteriaBuilder、CriteriaQuery、Rootエンティティを取得する
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);

        // パラメータにしたがって動的にPredicate（検索条件）を構築する
        Predicate condition = cb.conjunction();
        if (categoryId != null)
            condition = cb.and(condition, cb.equal(
                    book.get("category").get("categoryId"), categoryId));
        if (keyword != null && ! keyword.isEmpty())
            condition = cb.and(condition, cb.like(
                    book.get("bookName"), toLikeWord(keyword)));

        // CriteriaQueryを作成する
        cq.select(book).where(condition);

        // クエリを実行して結果を取得する
        List<Book> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

    private String toLikeWord(String keyword) {
        return "%" + keyword + "%";
    }
}