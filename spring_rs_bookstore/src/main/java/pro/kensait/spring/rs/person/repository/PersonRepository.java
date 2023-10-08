package pro.kensait.spring.rs.person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.kensait.spring.rs.person.service.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT p FROM Person p WHERE :lowerAge <= p.age")
    List<Person> selectByLowerAge(@Param("lowerAge") Integer lowerAge);
}