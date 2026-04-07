package com.example;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.easypoi_boot.pojo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * EasypoiBootApplicationTests111
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 15:13
 */

public class EasypoiBootApplicationTests111 {
    public List<User> getUser(){

        ArrayList<User> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUserName("文渊");
            user.setAge(16+i);
            user.setBirthday(new Date());
            user.setStatus(String.valueOf(i % 2));
            user.setHobbyList(Arrays.asList("抽烟","喝酒","烫头"));
            user.setCard(new Card("11023199010113210","北京朝阳区"));
//            user.setOrderList(Arrays.asList(new Order("1001","超短裙"),new Order("1001","棒棒糖")));
            user.setPhoto("C:\\Users\\86187\\Desktop\\ma.jpg");

            list.add(user);
        }
        return list;
    }

    @Test
    public void testExportExcel() throws Exception {

        //1.配置对象,2.导出类型,3.导出数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "测试"), User.class, getUser());

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\86187\\Desktop\\user.xls");
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

    @Test
    public void testImportExcel()throws Exception{

        ImportParams params = new ImportParams();
        params.setTitleRows(1); //标题列占几行
        params.setHeadRows(1); //列名占几行
        params.setNeedSave(true);
        params.setSaveUrl("I:\\msb_hejiayun\\easypoi_boot\\src\\main\\resources\\static");

        List<Emp> list = ExcelImportUtil.importExcel(new FileInputStream("C:\\Users\\86187\\Desktop\\emp.xls"), Emp.class, params);
        list.forEach(System.out::println);
    }


    //================================ 多sheet也导入方法=======================

    /**
     * 接收Excel文件导入的多个sheet页
     * @param filePath    导入文件路径
     * @param sheetIndex    导入sheet索引
     * @param titleRows     表标题行数
     * @param headerRows    表头行数
     * @param pojoClass     Excel实体
     * @return: java.util.List<T>
     */
    public static <T> List<T> importMultiSheet(String filePath,
                                               int sheetIndex,Integer titleRows,Integer headerRows,Class<T> pojoClass) throws FileNotFoundException {

        ImportParams params = new ImportParams();

        //设置参数
        params.setStartSheetIndex(sheetIndex); //sheet页下标
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);

        //表示表头必须包含的字段,不包含 就报错
        params.setImportFields(new String[]{"用户ID"});

        List<T> list = null;


        try {
            list = ExcelImportUtil.importExcel(new FileInputStream(filePath), pojoClass, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    //测试导入多个sheet页
    @Test
    public void testImportMultiSheet() throws Exception {

        String excelPath = "C:\\Users\\86187\\Desktop\\login.xls";

        List<LoginUser> loginUsers = importMultiSheet(excelPath, 0, 1, 1, LoginUser.class);
        loginUsers.forEach(System.out::println);

        List<LoginUrl> loginUrls = importMultiSheet(excelPath, 1, 1, 1, LoginUrl.class);
        loginUrls.forEach(System.out::println);

    }


    //================================ 多sheet页导出方法=======================

    /**
     * 导出多个sheet页方法
     * @param objects
     */
    public static void exportMultiSheet(Object... objects){

        //创建LoginUser导出对象
        ExportParams  loginUserParams = new ExportParams();

        //sheet1 置sheet名称
        loginUserParams.setSheetName("登录用户");
        loginUserParams.setTitle("登录用户列表");

        //使用map集合 封装sheet
        HashMap<String, Object> sheet1Map = new HashMap<>();

        //设置title
        sheet1Map.put("title",loginUserParams);
        sheet1Map.put("entity",LoginUser.class);

        //设置sheet页中的数据
        sheet1Map.put("data",objects[0]);


        //sheet2
        //创建LoginUser导出对象
        ExportParams  loginUrlParams = new ExportParams();

        //设置sheet名称
        loginUrlParams.setSheetName("URL路径");
        loginUrlParams.setTitle("URL路径");

        //使用map集合 封装sheet
        HashMap<String, Object> sheet2Map = new HashMap<>();

        //设置title
        sheet2Map.put("title",loginUrlParams);
        sheet2Map.put("entity",LoginUrl.class);

        //设置sheet页中的数据
        sheet2Map.put("data",objects[1]);

        //将sheet1和sheet2 包装起来
        List<Map<String, Object>> sheetList = new ArrayList<>();
        sheetList.add(sheet1Map);
        sheetList.add(sheet2Map);

        //执行导出方法
        Workbook workbook = ExcelExportUtil.exportExcel(sheetList, ExcelType.HSSF);

        try {
            workbook.write(new FileOutputStream("C:\\Users\\86187\\Desktop\\loginExport.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExportMultiSheet(){

        ArrayList<LoginUser> sheet1 = new ArrayList<>();
        sheet1.add(new LoginUser("1001","向阳","123456",new Date(),"0"));
        sheet1.add(new LoginUser("1002","于谦","123456",new Date(),"1"));
        sheet1.add(new LoginUser("1003","小月月","123456",new Date(),"0"));

        ArrayList<LoginUrl> sheet2 = new ArrayList<>();
        sheet2.add(new LoginUrl("1001","get","http://127.0.0.1:8080"));
        sheet2.add(new LoginUrl("1001","post","http://127.0.0.1:8080/login"));

        exportMultiSheet(sheet1,sheet2);
    }
}
