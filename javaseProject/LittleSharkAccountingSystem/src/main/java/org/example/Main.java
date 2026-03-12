package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-11 17:15
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalBalance = 0;
        ArrayList<Money> details = new ArrayList<>();
        System.out.println("-----------欢迎使用小鲨鱼记账系统-----------");
        while (true) {
            System.out.println("1.收支明细");
            System.out.println("2.登记收入");
            System.out.println("3.登记支出");
            System.out.println("4.退出");


            System.out.println("请选择你要使用的功能：");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("输入错误，请输入数字1-4！");
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("\n=====小鲨鱼记账系统---收支明细=====");
                    if (details.isEmpty()) {
                        System.out.println("📝 暂无收支记录！\n");
                    } else {
                        details.forEach(System.out::println);
                        System.out.println("💰 当前总余额：" + totalBalance + " 元\n");
                    }
                }
                case 2 -> {
                    System.out.println("\n=====小鲨鱼记账系统---登记收入=====");
                    Money m = new Money();
                    try {
                        System.out.print("收入金额：");
                        m.setIncome(sc.nextInt());
                        System.out.print("收入说明：");
                        m.setIncomeDescription(sc.next());
                        totalBalance += m.getIncome();
                        details.add(m);
                        System.out.println("✅ 收入登记成功！\n");
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("❌ 输入错误，收入登记失败！\n");
                    }

                }
                case 3 -> {
                    System.out.println("\n=====小鲨鱼记账系统---登记支出=====");
                    Money m = new Money();
                    try {
                        System.out.print("支出金额：");
                        m.setExpenditure(sc.nextInt());
                        if (m.getExpenditure() > totalBalance) {
                            System.out.println("❌ 支出金额超过当前总余额（" + totalBalance + "元），无法支出！\n");
                            break;
                        }
                        System.out.print("支出说明：");
                        m.setExpenditureDescription(sc.next());
                        totalBalance -= m.getExpenditure();
                        details.add(m);
                    } catch (Exception e) {
                        System.out.println("❌ 输入错误，支出登记失败！\n");
                    }
                }
                case 4 -> {
                    System.out.println("\n=====小鲨鱼记账系统---退出=====");
                    System.out.println("请确认是否退出：y/n");
                    String yesOrNo = sc.next();
                    if (yesOrNo.equalsIgnoreCase("y")) {
                        System.out.println("👋 感谢使用小鲨鱼记账系统，再见！");
                        sc.close();
                        return;
                    } else {
                        System.out.println("🔙 已取消退出，返回主菜单！\n");
                    }
                }
                default -> System.out.println("输入错误，请选择正确的功能选项！\n");
            }
        }
    }
}