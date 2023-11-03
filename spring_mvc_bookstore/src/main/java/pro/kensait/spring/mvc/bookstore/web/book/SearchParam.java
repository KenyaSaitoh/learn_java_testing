package pro.kensait.spring.mvc.bookstore.web.book;

public record SearchParam(
        Integer categoryId,
        String keyword) {
}
