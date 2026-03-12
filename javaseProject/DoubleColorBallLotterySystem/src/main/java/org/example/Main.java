package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * 双色球彩票系统主程序
 * @author Yang QingBo
 * @date 2026-03-12 08:31
 */
public class Main {
    // 开奖号码
    private static int[] redWinningNumbers = new int[6];
    private static int blueWinningNumber = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lottery lottery = new Lottery();

        System.out.println("=========欢迎进入双色球彩票系统=========");
        while (true) {
            System.out.println("\n【功能菜单】");
            System.out.println("1. 购买彩票");
            System.out.println("2. 查看开奖");
            System.out.println("3. 退出");
            System.out.print("请选择功能（1-3）：");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("❌ 输入错误！请输入1-3的整数");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> buyLottery(sc, lottery);
                case 2 -> checkWinning(lottery);
                case 3 -> {
                    if (confirmQuit(sc)) {
                        System.out.println("🎉 感谢使用双色球彩票系统，再见！");
                        sc.close();
                        return;
                    }
                }
                default -> System.out.println("❌ 无效选项！请选择1-3的整数");
            }
        }
    }

    /**
     * 购买彩票功能
     */
    private static void buyLottery(Scanner sc, Lottery lottery) {
        System.out.println("\n=========购买彩票=========");
        // 1. 输入下注数量
        System.out.print("请输入下注数量（至少1注）：");
        int betNumber;
        try {
            betNumber = sc.nextInt();
            lottery.setNumber(betNumber);
        } catch (Exception e) {
            System.out.println("❌ 下注数量输入错误，已默认设置为1注");
            sc.nextLine(); // 清空错误输入
            lottery.setNumber(1);
        }

        // 2. 输入6个红球号码
        int[] redBalls = new int[6];
        for (int i = 0; i < 6; ) {
            System.out.print("请输入第" + (i+1) + "个红球号码（1-33，不重复）：");
            try {
                int redBall = sc.nextInt();
                // 校验范围
                if (redBall < 1 || redBall > 33) {
                    System.out.println("❌ 红球号码必须在1-33之间，请重新输入");
                    continue;
                }
                // 校验是否重复
                boolean isDuplicate = false;
                for (int j = 0; j < i; j++) {
                    if (redBalls[j] == redBall) {
                        System.out.println("❌ 红球号码不能重复，请重新输入");
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    redBalls[i] = redBall;
                    i++; // 只有输入有效才递增索引
                }
            } catch (Exception e) {
                System.out.println("❌ 红球号码输入错误（必须是整数），请重新输入");
                sc.nextLine(); // 清空错误输入
            }
        }
        lottery.setRedBall(redBalls);

        // 3. 输入蓝球号码
        int blueBall;
        while (true) {
            System.out.print("请输入蓝球号码（1-16）：");
            try {
                blueBall = sc.nextInt();
                lottery.setBlueBall(blueBall);
                break;
            } catch (Exception e) {
                System.out.println("❌ 蓝球号码输入错误（必须是整数），请重新输入");
                sc.nextLine(); // 清空错误输入
            }
        }

        // 4. 打印购买信息
        System.out.println("\n✅ 购买成功！");
        System.out.println("下注数量：" + lottery.getNumber() + "注");
        System.out.println("花费金额：" + lottery.getNumber() * 2 + "元");
        System.out.print("所选号码：红球[");
        for (int i = 0; i < lottery.getRedBall().length; i++) {
            System.out.print(lottery.getRedBall()[i]);
            if (i != lottery.getRedBall().length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]，蓝球[" + lottery.getBlueBall() + "]");
    }

    /**
     * 查看开奖功能
     */
    private static void checkWinning(Lottery lottery) {
        System.out.println("\n=========查看开奖=========");
        // 1. 生成不重复的开奖号码
        generateWinningNumbers();

        // 2. 打印开奖号码
        System.out.print("本期开奖号码：红球[");
        for (int i = 0; i < redWinningNumbers.length; i++) {
            System.out.print(redWinningNumbers[i]);
            if (i != redWinningNumbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]，蓝球[" + blueWinningNumber + "]");

        // 3. 打印用户所选号码
        System.out.print("您所选号码：红球[");
        for (int i = 0; i < lottery.getRedBall().length; i++) {
            System.out.print(lottery.getRedBall()[i]);
            if (i != lottery.getRedBall().length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]，蓝球[" + lottery.getBlueBall() + "]");

        // 4. 计算中奖个数
        int redMatchCount = countRedMatch(lottery.getRedBall());
        boolean blueMatch = lottery.getBlueBall() == blueWinningNumber;
        int blueMatchCount = blueMatch ? 1 : 0;

        // 5. 打印匹配结果
        System.out.println("\n🎯 匹配结果：");
        System.out.println("红球匹配个数：" + redMatchCount);
        System.out.println("蓝球匹配个数：" + blueMatchCount);

        // 6. 判断奖项并计算奖金
        String awardLevel = getAwardLevel(redMatchCount, blueMatch);
        double totalBonus = calculateBonus(awardLevel, lottery.getNumber());
        System.out.println("🏆 您中得：" + awardLevel);
        System.out.println("💰 总奖金：" + (totalBonus > 0 ? totalBonus + "元" : "0元"));
    }

    /**
     * 生成不重复的开奖号码
     */
    private static void generateWinningNumbers() {
        Random random = new Random();
        // 生成6个不重复的红球（1-33）
        HashSet<Integer> redSet = new HashSet<>();
        while (redSet.size() < 6) {
            int redNum = random.nextInt(33) + 1;
            redSet.add(redNum);
        }
        // 转换为数组
        int index = 0;
        for (int num : redSet) {
            redWinningNumbers[index++] = num;
        }
        // 生成蓝球（1-16）
        blueWinningNumber = random.nextInt(16) + 1;
    }

    /**
     * 计算红球匹配个数
     */
    private static int countRedMatch(int[] userRedBalls) {
        int count = 0;
        for (int userNum : userRedBalls) {
            for (int winningNum : redWinningNumbers) {
                if (userNum == winningNum) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 判断奖项等级
     */
    private static String getAwardLevel(int redMatch, boolean blueMatch) {
        if (redMatch == 6 && blueMatch) {
            return "一等奖（最高500万元）";
        } else if (redMatch == 6) {
            return "二等奖（最高500万元）";
        } else if (redMatch == 5 && blueMatch) {
            return "三等奖（3000元）";
        } else if (redMatch == 5 || (redMatch == 4 && blueMatch)) {
            return "四等奖（200元）";
        } else if (redMatch == 4 || (redMatch == 3 && blueMatch)) {
            return "五等奖（10元）";
        } else if (blueMatch) { // 0/1/2个红球 + 中蓝球
            return "六等奖（5元）";
        } else {
            return "未中奖";
        }
    }

    /**
     * 计算总奖金
     */
    private static double calculateBonus(String awardLevel, int betNumber) {
        return switch (awardLevel) {
            case "一等奖（最高500万元）" -> 5000000.0 * betNumber;
            case "二等奖（最高500万元）" -> 200000.0 * betNumber;
            case "三等奖（3000元）" -> 3000.0 * betNumber;
            case "四等奖（200元）" -> 200.0 * betNumber;
            case "五等奖（10元）" -> 10.0 * betNumber;
            case "六等奖（5元）" -> 5.0 * betNumber;
            default -> 0.0;
        };
    }

    /**
     * 确认是否退出系统
     */
    private static boolean confirmQuit(Scanner sc) {
        System.out.print("\n请确认是否退出（y/n）：");
        String input = sc.next();
        return input.equalsIgnoreCase("y");
    }
}