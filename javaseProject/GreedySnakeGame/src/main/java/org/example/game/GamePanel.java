package org.example.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-12 12:03
 */

public class GamePanel extends JPanel {
    //定义蛇的长度：
    private int length;
    //定义一个数组，专门存储蛇的x轴坐标：
    int[] snakeX = new int[600];
    //定义一个数组，专门存储蛇的y轴坐标：
    int[] snakeY = new int[600];
    //设置食物的xy轴坐标：
    int foodX;
    int foodY;
    //设置一个变量，定义小蛇的方向：
    String direction = "R"; //方向： L   R  U  D
    //定义一个变量，控制游戏是否开始：
    boolean flag = false;
    //创建一个定时器：
    Timer timer;
    //定义一个变量，专门用来接收积分：
    int score;
    //加入一个变量，判断蛇的生死：
    boolean isDie = false;//默认蛇不是死的

    //初始化方法：
    public void init() {
        length = 3;//蛇的初始化长度为3
        //初始化蛇头的坐标：
        snakeX[0] = 150;
        snakeY[0] = 300;
        //初始化第一节蛇身子坐标：
        snakeX[1] = 125;
        snakeY[1] = 300;
        //初始化第二节蛇身子坐标：
        snakeX[2] = 100;
        snakeY[2] = 300;
        //初始化食物的位置：
        foodX = 325;
        foodY = 225;
        // 重置积分
        score = 0;
        // 重置方向
        direction = "R";

    }

    public GamePanel() {
        init();
        //将焦点放在面板上：
        this.setFocusable(true);
        //加入一个键盘监听：
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                System.out.println(key);
                if (key == KeyEvent.VK_SPACE) {//监听到空格了
                    if (isDie) {
                        //蛇如果死了，点击空格后 恢复最开始的状态：
                        init();
                        isDie = false;//由死变生
                    } else {
                        /* System.out.println("你已经点击空格了");*/
                        flag = !flag;
                        //游戏如果是开始的，就变为暂停的
                        //游戏如果是暂停的，就变为开始的
                        repaint();//重新绘制
                    }
                }
                //上
                if (key == KeyEvent.VK_UP) {
                    direction = "U";
                }
                //下
                if (key == KeyEvent.VK_DOWN) {
                    direction = "D";
                }
                //左
                if (key == KeyEvent.VK_LEFT) {
                    direction = "L";
                }

                //右
                if (key == KeyEvent.VK_RIGHT) {
                    direction = "R";
                }
            }
        });


        //初始化定时器：创建定时器的对象
        //参数两个：第一个参数设置：蛇多久动一次
        //第二个参数：就是设置蛇具体怎么动-->重写了一个actionPerformed方法
        //具体蛇头的逻辑要写在actionPerformed方法中
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag && !isDie) {//如果游戏是开始的，蛇才会动
                    //蛇的后一节 到 前一节位置上去：
                    //实际就是改变蛇的坐标 -->改变的蛇身子
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    //改变蛇头：
                    //往右动的话 蛇头 x坐标+25
                    if (direction.equals("R")) {
                        snakeX[0] = snakeX[0] + 25;
                    }
                    if (direction.equals("L")) {
                        snakeX[0] = snakeX[0] - 25;
                    }
                    if (direction.equals("U")) {
                        snakeY[0] = snakeY[0] - 25;
                    }
                    if (direction.equals("D")) {
                        snakeY[0] = snakeY[0] + 25;
                    }


                    //防止蛇飞出边界：
                    //向右边界：
                    if (snakeX[0] > 750) {
                        snakeX[0] = 25;
                    }
                    //向上边界：
                    if (snakeY[0] < 100) {
                        snakeY[0] = 725;
                    }

                    //向左边界：
                    if (snakeX[0] < 25) {
                        snakeX[0] = 750;
                    }

                    //向下边界：
                    if (snakeY[0] > 725) {
                        snakeY[0] = 100;
                    }

                    //当蛇头和食物的坐标重合的时候就是吃到食物了：
                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        //蛇长度加1：
                        length++;
                        //改变食物的位置；-->重点：食物的坐标 无论是x  还是y必须是25的倍数
                        foodX = 25 + 25 * (new Random().nextInt(28));
                        foodY = 100 + 25 * (new Random().nextInt(25));
                        //积分增长:
                        score = (length - 3) * 10;
                    }
                    /*
                    [25,725] --> [0,700]+25
                    Math.random -- >  [0.0,1.0)
                    Math.random*701 --> [0.0,700.0)
                    (int)(Math.random*701)  -->[0,700]
                    (int)(Math.random*701)+ 25

                    [100,750] -->[0,650]+100
                     */
                    //加入一个死亡判定：蛇头跟身子的任意一节重合，都失败
                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isDie = true;
                        }
                    }
                    //蛇的坐标改完以后，重绘这个画面
                    repaint();
                }
            }
        });
        //定时器开启：
        timer.start();
    }


    /*
    paintComponent这个方法 就类似 我们平时写的main方法
    它能做的事就是 ：底层也会直接调用，只要你写了这个方法，当需要画图的时候
    底层就去自动调用这个方法
    所以你想要画的东西，都要写在这个方法中
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //先将面板的背景颜色画上：
        this.setBackground(new Color(174, 207, 174));
        //画一个图片：
        /*
        四个参数：this指代当前面板   g利用画笔   x,y指的就是举例 面板左上角的坐标
         */
        Images.headerImg.paintIcon(this, g, 10, 10);
        //调节画笔的颜色：
        g.setColor(new Color(190, 179, 201));
        //画一个矩形：
        /*
        四个参数：x,y  设置坐标   后面两个参数：设置矩形的宽和高
         */
        g.fillRect(10, 70, 770, 685);
        //画蛇头：
        if (direction.equals("R")) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if (direction.equals("L")) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if (direction.equals("U")) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if (direction.equals("D")) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        /*//画第一节身子：
        Images.bodyImg.paintIcon(this,g,snakeX[1],snakeY[1]);
        //画第二节身子：
        Images.bodyImg.paintIcon(this,g,snakeX[2],snakeY[2]);*/
        //将画身子的方法优化：
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //画食物：
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        //判断游戏到底是开始还是暂停：
        if (flag == false) {//游戏暂停：
            //在面板上写文字：写游戏暂停的文字：
            g.setColor(new Color(114, 98, 255));
            //三个参数：字体样式，加粗效果，字号
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            //写文字
            g.drawString("点击空格开始游戏！", 250, 330);
        }/*else{//游戏开始
            //什么都不用动。。不用管
        }*/

        //画积分：

        g.setColor(new Color(255, 248, 248));
        //三个参数：字体样式，加粗效果，字号
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        //写文字
        g.drawString("积分：" + score, 620, 40);

        //根据蛇的死亡状态来在画布上画内容：
        if (isDie) {
            g.setColor(new Color(255, 90, 210));
            //三个参数：字体样式，加粗效果，字号
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            //写文字
            g.drawString("小蛇死亡，游戏停止", 250, 330);
            g.drawString("按下空格重新开始游戏！", 250, 360);
        }
    }
}