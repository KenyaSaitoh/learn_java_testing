package pro.kensait.spring.bookstore.web.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class TokenProcessor {
    private static final Logger logger = LoggerFactory.getLogger(
            TokenProcessor.class);

    @Autowired
    private HttpSession session;

    public void setUp(Object principal, Object credentials) {
        logger.info("[ TokenProcessor#setUp ]");
        // 認証済みのトークンを生成する
        List<GrantedAuthority> authorities = new ArrayList<>();
        Authentication token = UsernamePasswordAuthenticationToken
                .authenticated(principal, credentials,
                        authorities);

        // SecurityContextHolderに、認証済みトークンを登録する
        SecurityContextHolder.getContext().setAuthentication(token);

        // HTTPセッションに、認証済みを表すSecurityContextを登録する
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }
}