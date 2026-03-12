package org.example.util;

import org.example.poji.BaconPizza;
import org.example.poji.FruitPizza;
import org.example.poji.Pizza;

import java.util.Scanner;

/**
 * @author Yang QingBo
 * @date 2026-03-12 11:09
 */
public class PizzaStore {
    public static Pizza getPizza(int choice) {
        Scanner sc = new Scanner(System.in);
        Pizza p = null;
        switch (choice) {
            case 1 -> {
                System.out.println("请输入培根的克数：");
                int grams = sc.nextInt();
                System.out.println("请输入披萨的大小：");
                int size = sc.nextInt();
                System.out.println("请输入披萨的价格：");
                double price = sc.nextDouble();
                p = new BaconPizza("培根披萨", size, price, grams);
            }
            case 2 -> {
                System.out.println("请录入你想加入的水果：");
                String fruit = sc.nextLine();
                System.out.println("请输入披萨的大小：");
                int size = sc.nextInt();
                System.out.println("请输入披萨的价格：");
                double price = sc.nextDouble();
                p = new FruitPizza("水果披萨", size, price, fruit);
            }
        }
        return p;
    }
}
