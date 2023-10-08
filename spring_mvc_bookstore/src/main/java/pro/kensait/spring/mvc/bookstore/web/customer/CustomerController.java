package pro.kensait.spring.mvc.bookstore.web.customer;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pro.kensait.spring.mvc.bookstore.entity.Customer;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerExistsException;
import pro.kensait.spring.mvc.bookstore.service.customer.CustomerService;
import pro.kensait.spring.mvc.bookstore.web.cart.CartController;

public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(
            CartController.class);

    // アクションメソッド（ログイン）
    public String login() {
        // 入力された電子メールをキーにデータベースから顧客を検索する（JPA）
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("MyPersistenceUnit2");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(
                "SELECT c FROM Customer AS c " +
                "WHERE c.email = :email")
                .setParameter("email", email);
        Customer customer;
        try {
            customer = (Customer)query.getSingleResult();
        } catch(NoResultException nre) {
            customer = null;
        }
        // 顧客の存在チェックおよびパスワードの一致チェックを行う
        if (customer != null && password.equals(customer.getPassword())) {
            // チェックがOKだった場合は、このオブジェクトに顧客データを格納する
            customerId = customer.getCustomerId();
            customerName = customer.getCustomerName();
            address = customer.getAddress();
        } else {
            // チェックがNGだった場合は、エラーメッセージを生成して登録する
            FacesMessage message = 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "ログインエラー",
                            "ログインできませんでした");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
            return "error";
        }
        return "success";
    }
    
    // アクションメソッド（ログアウト）
    public String logout() {
        // セッションマップを取得する
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = 
                context.getExternalContext().getSessionMap();
        
        // セッションマップをクリアする
        sessionMap.clear();
        return "success";
    }
    
    // アクションメソッド（顧客を登録する）
    public String register() {
        // セッションBeanオブジェクトをJNDIルックアップにより取得する
        CustomerService customerService = null;
        try {
            Context context = new InitialContext();
            customerService = (CustomerService)context
                    .lookup("java:comp/env/ejb/CustomerServiceRefName");
        } catch(NamingException ne) {
            throw new RuntimeException(ne);
        }
        
        // セッションBeanのビジネスメソッド（顧客登録処理）を呼び出す
        try {
            customerId = customerService.registerCustomer(customerName, 
                    password, email, birthday, address);
        } catch(CustomerExistsException cee) {
            FacesMessage message = 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "登録済み",
                        "すでに登録されています");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, message);
            return "error";
        }
        
        // セッションマップを取得する
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = 
                context.getExternalContext().getSessionMap();
        
        // セッションからCustomerBeanオブジェクトを取得する
        CustomerParam customer = 
                (CustomerParam)sessionMap.get("customer");
        
        // CustomerBeanオブジェクトに、顧客登録された情報を格納する
        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        customer.setEmail(email);
        customer.setAddress(address);
        
        return "success";
    }
}