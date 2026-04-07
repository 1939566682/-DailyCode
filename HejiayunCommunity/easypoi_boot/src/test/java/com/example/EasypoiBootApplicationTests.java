package com.example;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.easypoi_boot.pojo.Card;
import com.example.easypoi_boot.pojo.Order;
import com.example.easypoi_boot.pojo.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

class EasypoiBootApplicationTests {

    /**
     * 导出真实数据Excel（用户+身份证+订单）
     */
    @Test
    void testExportExcel() {
        Workbook workbook = ExcelExportUtil.exportBigExcel(
                new ExportParams("用户信息明细表", "用户数据"), // 真实表格标题
                User.class,
                getUsers()
        );

        try {
            // 保持你原来的导出路径
            FileOutputStream out = new FileOutputStream("D:\\Storage\\OneDrive\\桌面\\快捷包\\users.xlsx");
            workbook.write(out);
            out.close();
            workbook.close();
            System.out.println("✅ 导出成功！数据全部为真实场景格式，可直接用于测试/演示！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 真实姓名库（避免xxx_0，贴合实际）
    private static final String[] REAL_NAMES = {"张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十", "钱十一", "冯十二"};
    // 真实地址库（分省市，合理不随机）
    private static final String[] REAL_ADDRESSES = {
            "北京市朝阳区建国路88号", "上海市浦东新区张江高科技园区", "广东省深圳市南山区科技园",
            "浙江省杭州市西湖区文三路", "江苏省南京市玄武区中山陵路", "山东省济南市历下区泉城广场",
            "四川省成都市锦江区春熙路", "湖北省武汉市武昌区中北路", "陕西省西安市雁塔区大雁塔路",
            "广东省广州市天河区天河城"
    };
    // 真实订单名称（贴合日常场景）
    private static final String[] ORDER_NAMES = {
            "小米14手机 1台", "华为MateBook Pro 笔记本", "星巴克拿铁咖啡 2杯",
            "美团外卖 午餐订单", "京东超市 生活用品采购", "顺丰快递 寄件服务",
            "优酷会员 年卡充值", "滴滴出行 打车订单", "淘宝服饰 连衣裙", "拼多多 水果采购"
    };
    // 真实爱好（合理组合，不重复乱造）
    private static final List<String> HOBBY_POOL = Arrays.asList("看书", "跑步", "健身", "摄影", "钓鱼", "做饭", "追剧", "爬山");

    private static final String[] PHOTOS = {
            "D:\\Storage\\OneDrive\\图片\\本机照片\\阿柒.jpg",
            "D:\\Storage\\OneDrive\\图片\\本机照片\\落雨.jpg"
    };

    /**
     * 生成【真实场景】用户数据（包含：真实姓名、真实地址、合规身份证、合理订单）
     */
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId("USER_" + new java.text.SimpleDateFormat("yyyyMMdd").format(new Date()) + "_" + String.format("%02d", i+1));
            user.setUserName(REAL_NAMES[i]);
            user.setPassword(generateRealPassword());
            user.setAge(18 + random.nextInt(28));

            // 生日
            Calendar calendar = Calendar.getInstance();
            calendar.set(1980 + random.nextInt(29), random.nextInt(12), random.nextInt(28));
            user.setBirthday(calendar.getTime());

            user.setStatus(i < 8 ? "1" : "0");
            user.setHobbyList(getRandomHobbies());
            user.setCard(new Card(generateRealIdCard(), REAL_ADDRESSES[i]));
            user.setOrderList(generateRealOrders(i, random));

            // ===================== 【自动分配头像】 =====================
            // 奇数用户用第一张，偶数用户用第二张
            if (i % 2 == 0) {
                user.setPhoto(PHOTOS[0]);
            } else {
                user.setPhoto(PHOTOS[1]);
            }

            userList.add(user);
        }
        return userList;
    }

    /**
     * 生成真实密码（字母+数字，8-9位，贴合日常）
     */
    private String generateRealPassword() {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // 至少1个大写、1个小写、1个数字
        sb.append(letters.charAt(random.nextInt(26))); // 小写
        sb.append(letters.charAt(random.nextInt(26) + 26)); // 大写
        sb.append(numbers.charAt(random.nextInt(10))); // 数字
        // 补全到8-9位
        int length = 8 + random.nextInt(2);
        for (int i = 3; i < length; i++) {
            sb.append(random.nextBoolean() ? letters.charAt(random.nextInt(52)) : numbers.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * 生成合规18位身份证号（前6位真实地区码，中间8位生日，后4位随机合规）
     */
    private String generateRealIdCard() {
        // 真实地区码（10个不同省份/城市）
        String[] areaCodes = {"110101", "310101", "440305", "330106", "320102", "370102", "510104", "420106", "610113", "440106"};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // 1. 前6位：地区码
        sb.append(areaCodes[random.nextInt(10)]);
        // 2. 中间8位：生日（1980-2008年）
        int year = 1980 + random.nextInt(29);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        sb.append(String.format("%04d%02d%02d", year, month, day));
        // 3. 后3位：顺序码
        sb.append(String.format("%03d", random.nextInt(1000)));
        // 4. 最后1位：校验码（0-9或X）
        String checkCodes = "0123456789X";
        sb.append(checkCodes.charAt(random.nextInt(11)));
        return sb.toString();
    }

    /**
     * 生成随机3个爱好（不重复，贴合真实）
     */
    private List<String> getRandomHobbies() {
        Random random = new Random();
        List<String> hobbies = new ArrayList<>();
        // 随机选3个不重复的爱好
        while (hobbies.size() < 3) {
            String hobby = HOBBY_POOL.get(random.nextInt(HOBBY_POOL.size()));
            if (!hobbies.contains(hobby)) {
                hobbies.add(hobby);
            }
        }
        return hobbies;
    }

    /**
     * 生成真实订单（1-3个/用户，唯一订单号，真实订单名称）
     */
    private List<Order> generateRealOrders(int userId, Random random) {
        List<Order> orderList = new ArrayList<>();
        // 每个用户1-3个订单
        int orderCount = 1 + random.nextInt(3);
        for (int j = 0; j < orderCount; j++) {
            Order order = new Order();
            // 订单号（真实业务格式：前缀+时间戳+用户序号+订单序号）
            order.setId("ORDER_" + System.currentTimeMillis() + "_" + (userId+1) + "_" + (j+1));
            // 订单名称（随机选真实场景订单）
            order.setName(ORDER_NAMES[random.nextInt(ORDER_NAMES.length)]);
            orderList.add(order);
        }
        return orderList;
    }


}