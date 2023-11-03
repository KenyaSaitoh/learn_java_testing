package pro.kensait.spring.mvc.bookstore.service.login;

public record LoginTO(
        // メールアドレス
        String email,
        // パスワード
        String password) {
}