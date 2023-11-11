package pro.kensait.spring.mvc.bookstore.service.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;

@Service
@Transactional
public class LoginService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(
            LoginService.class);

    @Autowired
    private CustomerRepository customerRepos;

    // ログイン
    /*
    public Customer login(LoginTO loginTO) throws CustomerNotFoundException {
        // 入力された電子メールをキーにデータベースから顧客を検索する
        Optional<Customer> customerOpt = customerRepos.findCustomerByEmail(loginTO.email());

        // 顧客の存在チェックおよびパスワードの一致チェックを行う
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (loginTO.password().equals(customer.getPassword())) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("顧客が存在しません");
    }
    */

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println("############ LoginService#loadUserByUsername");
        Customer Customer = customerRepos.findCustomerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with this email address."));
        List<GrantedAuthority> authorities = new ArrayList<>();
        User customer = new User(Customer.getCustomerName(), Customer.getPassword(),
                authorities);
        System.out.println("USER####" + customer);
        return customer;
    }
}