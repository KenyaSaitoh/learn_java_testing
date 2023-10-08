package pro.kensait.spring.mvc.bookstore.web.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pro.kensait.spring.mvc.bookstore.entity.Book;
import pro.kensait.spring.mvc.bookstore.entity.Category;
import pro.kensait.spring.mvc.bookstore.service.CartItemTO;
import pro.kensait.spring.mvc.bookstore.service.order.OrderRecord;
import pro.kensait.spring.mvc.bookstore.web.cart.CartParam;
import pro.kensait.spring.mvc.bookstore.web.customer.CustomerParam;

public class OrderController {

    // カテゴリリストへのアクセサメソッド
    public List<SelectItem> getCategoryList() {
        // 初期化する
        if (categoryList == null) {
            categoryList = new ArrayList<SelectItem>();
            categoryList.add(new SelectItem("0", ""));
            
            // データベースからカテゴリを検索する（JPA）
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("MyPersistenceUnit2");
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery(
                    "SELECT c FROM Category AS c");
            List<Category> resultList = query.getResultList();
            
            // 取得したCategoryオブジェクトからSelectItemオブジェクトを生成する
            Iterator<Category> i = resultList.iterator();
            while (i.hasNext()) {
                Category category = i.next();
                categoryList.add(new SelectItem(category.getCategoryId(), 
                        category.getCategoryName()));
            }
        }
        
        return categoryList;
    }
    
    public void setCategoryList(List<SelectItem> categoryList) {
        this.categoryList = categoryList;
    }
    
    // カートへのアクセサメソッド
    public CartParam getCart() {
        return cart;
    }

    public void setCart(CartParam cart) {
        this.cart = cart;
    }
 
    // アクションメソッド（書籍を検索する） 
    public String search() {
        // BookBeanオブジェクトを格納するリストを生成する
        List<BookForm> bookBeanList = new ArrayList<BookForm>();

        // 入力された検索条件から書籍を検索する
        List<Book> bookEntityList = null;
        if(categoryId == 0) {
            bookEntityList = bookService.searchBook(keyword);
        } else {
            bookEntityList = bookService.searchBook(categoryId, keyword);
        }
        
        // 取得したBookオブジェクト（エンティティオブジェクト）のリストから
        // BookBeanオブジェクトを生成し、それらをリスト（bookBeanList）に格納する
        Iterator<Book> i = bookEntityList.iterator();
        while (i.hasNext()) {
            Book book = i.next();
            BookForm bookBean = new BookForm(book.getBookId(),
                    book.getBookName(), 
                    book.getAuthor(),
                    book.getCategory().getCategoryName(), 
                    book.getPublisher().getPublisherName(), 
                    book.getPrice());
            bookBeanList.add(bookBean);
        }

        // 生成したリスト（bookBeanList）をデータモデルに登録する
        cart.getBookTable().setValue(bookBeanList);
        
        return "success";
    }

    
    // アクションメソッド（買い物カゴに入れた書籍を注文する）
    public String order() {
        // セッションマップを取得する
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = 
                context.getExternalContext().getSessionMap();
        
        // セッションマップからCustomerBeanオブジェクトを取得する
        CustomerParam customer = (CustomerParam)sessionMap.get("customer");
        
        // CartItemBeanオブジェクトのリストのコピーを生成する
        List<CartItemTO> cartItems1 = cart.getCartItems();
        List<CartItemTO> CartItems2 = new ArrayList<CartItemTO>();
        Iterator<CartItemTO> i = cartItems1.iterator();
        while (i.hasNext()) {
            CartItemTO cartItem = i.next();
            CartItems2.add(cartItem);
        }
        
        // トランスファーオブジェクトを生成する
        OrderRecord orderTO = new OrderRecord(
                customer.getCustomerId(),
                LocalDate.now(),
                CartItems2,
                cart.getTotalPrice(),
                cart.getDeliveryPrice(),
                cart.getDeliveryAddress(),
                cart.getSettlementType());

        // セッションBeanのビジネスメソッド（注文処理）を呼び出す
        bookService.orderBooks(orderTO);

        // セッションマップからカートを削除する
        sessionMap.remove("cart");

        return "success";
    }
}