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

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.repository.CustomerRepository;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(
            UserService.class);

    @Autowired
    private CustomerRepository customerRepos;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println("############ LoginService#loadUserByUsername");
        Customer customer = customerRepos.findCustomerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with this email address."));
        List<GrantedAuthority> authorities = new ArrayList<>();
        User user = new User(customer.getCustomerName(), customer.getPassword(),
                authorities);
        System.out.println("USER####" + user);
        return user;
    }
}