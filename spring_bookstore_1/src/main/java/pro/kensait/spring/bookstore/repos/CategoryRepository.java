package pro.kensait.spring.bookstore.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.bookstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}