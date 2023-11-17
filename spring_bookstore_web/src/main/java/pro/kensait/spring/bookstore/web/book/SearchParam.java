package pro.kensait.spring.bookstore.web.book;

public record SearchParam(
        Integer categoryId,
        String keyword) {
}
