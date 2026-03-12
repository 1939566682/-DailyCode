package org.example;

import java.util.Arrays;

/**
 * 双色球彩票实体类
 * @author Yang QingBo
 * @date 2026-03-12 08:46
 */
public class Lottery {
    private int number; // 下注数量（至少1注）
    private int[] redBall = new int[6]; // 红球号码（1-33，不重复）
    private int blueBall; // 蓝球号码（1-16）

    public Lottery() {
    }

    public Lottery(int number, int[] redBall, int blueBall) {
        setNumber(number); // 用set方法做校验
        setRedBall(redBall); // 用set方法做校验
        setBlueBall(blueBall); // 用set方法做校验
    }

    // 获取下注数量
    public int getNumber() {
        return number;
    }

    // 设置下注数量（校验：至少1注）
    public void setNumber(int number) {
        if (number < 1) {
            System.out.println("⚠️ 下注数量不能小于1，已默认设置为1注");
            this.number = 1;
        } else {
            this.number = number;
        }
    }

    // 获取红球号码
    public int[] getRedBall() {
        return redBall;
    }

    // 设置红球号码（校验：1-33，6个不重复）
    public void setRedBall(int[] redBall) {
        if (redBall == null || redBall.length != 6) {
            System.out.println("⚠️ 红球必须输入6个号码，已重置为默认值");
            this.redBall = new int[6];
            return;
        }

        // 校验每个红球的范围和唯一性
        boolean isValid = true;
        for (int i = 0; i < redBall.length; i++) {
            int num = redBall[i];
            // 范围校验
            if (num < 1 || num > 33) {
                System.out.println("⚠️ 红球号码必须在1-33之间，第" + (i+1) + "个号码" + num + "无效");
                isValid = false;
                break;
            }
            // 唯一性校验
            for (int j = i+1; j < redBall.length; j++) {
                if (redBall[i] == redBall[j]) {
                    System.out.println("⚠️ 红球号码不能重复，第" + (i+1) + "个和第" + (j+1) + "个号码都是" + num);
                    isValid = false;
                    break;
                }
            }
            if (!isValid) break;
        }

        if (isValid) {
            this.redBall = redBall;
        } else {
            System.out.println("⚠️ 红球号码无效，已重置为默认值");
            this.redBall = new int[6];
        }
    }

    // 获取蓝球号码
    public int getBlueBall() {
        return blueBall;
    }

    // 设置蓝球号码（校验：1-16）
    public void setBlueBall(int blueBall) {
        if (blueBall < 1 || blueBall > 16) {
            System.out.println("⚠️ 蓝球号码必须在1-16之间，已默认设置为1");
            this.blueBall = 1;
        } else {
            this.blueBall = blueBall;
        }
    }

    @Override
    public String toString() {
        return "彩票信息：" +
                "下注" + number + "注，" +
                "红球：" + Arrays.toString(redBall) +
                "，蓝球：" + blueBall;
    }
}