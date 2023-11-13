package pro.kensait.spring.mvc.bookstore.web.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginParam(
        // メールアドレス
        @NotEmpty
        String email,
        // パスワード
        @NotEmpty
        @Size(min = 4, max = 10)
        String password) {
}