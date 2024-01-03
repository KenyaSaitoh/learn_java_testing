package pro.kensait.java.coverage;

import java.util.Scanner;

public class Main_Coverage_2 {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        System.out.print("totalPrice? => ");
        int totalPrice = Integer.parseInt(new Scanner(System.in).nextLine());

        // 命令網羅ではなく分岐網羅させたい
        int deliveryFee = 10000 <= totalPrice ? 300 : 900;

        System.out.println("deliveryFee => " + deliveryFee);
    }
}
