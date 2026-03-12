package org.example;

import org.example.poji.Pizza;
import org.example.util.PizzaStore;

import java.util.Scanner;

/**
 * @author Yang QingBo
 * @date 2026-03-12 10:42
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择想要制作的披萨：");
        System.out.println("1.培根披萨");
        System.out.println("2.水果披萨");
        int choice = sc.nextInt();
        Pizza pizza = PizzaStore.getPizza(choice);
        System.out.println(pizza.toString());
    }
}