package pro.kensait.spring.mvc.bookstore.entity.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class JpaTestBase {
    public EntityManagerFactory emf;
    public EntityManager em;
    public EntityTransaction et;

    // テストメソッド呼び出し前処理
    @BeforeEach
    public void beforeTest() {
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    // テストメソッド呼び出し後処理
    @AfterEach
    public void afterTest() {
        try {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // コミット
    public void commit() {
        try {
            et.commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}