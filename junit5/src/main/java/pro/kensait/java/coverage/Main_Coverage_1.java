package pro.kensait.java.coverage;

import java.util.Scanner;

public class Main_Coverage_1 {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        System.out.print("totalPrice? => ");
        int totalPrice = Integer.parseInt(new Scanner(System.in).nextLine());

        // 分岐網羅させたい
        int deliveryFee;
        if (10000 <= totalPrice) {
            deliveryFee = 300;
        } else {
            deliveryFee = 900;
        }

        System.out.println("deliveryFee => " + deliveryFee);
    }
}
