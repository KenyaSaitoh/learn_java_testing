package pro.kensait.spring.mvc.bookstore.web.login;

import jakarta.validation.constraints.NotEmpty;

public record LoginParam(
        // メールアドレス
        @NotEmpty
        String email,
        // パスワード
        @NotEmpty
        String password) {
}