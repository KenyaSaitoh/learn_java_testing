package pro.kensait.java.shipping;

import java.util.ArrayList;
import java.util.List;

public class ShippingRepository {
    public static void save(Shipping shipping) {
        shippingList.add(shipping);
    }

    public static List<Shipping> shippingList = new ArrayList<>();
}