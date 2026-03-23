package org.example.springdiy;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.xml.sax.*;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 09:54
 */

public class MyClassPathXmlApplicationContext implements MyFactory {
    List<MyBean> listBeans = new ArrayList<>();
    Map<String, Object> beanMap = new HashMap<>();

    public MyClassPathXmlApplicationContext(String xmlPath) {
        parseXml(xmlPath); // 解析XML
        createBeanInstances(); // 创建Bean实例
    }

    // 核心修复：移除不兼容的setFeature，简化XML解析逻辑
    private void parseXml(String xmlPath) {
        try {
            SAXReader reader = new SAXReader();
            // 移除导致异常的setFeature代码，兼容所有解析器

            // 1. 检查配置文件是否存在
            URL resourceUrl = getClass().getClassLoader().getResource(xmlPath);
            if (resourceUrl == null) {
                throw new RuntimeException("配置文件不存在：" + xmlPath + "，请检查文件路径是否正确");
            }

            // 2. 读取XML文档（自动忽略DTD验证，无需额外配置）
            Document doc = reader.read(resourceUrl);

            // 3. 直接获取根节点（beans）下的所有bean子节点（兼容Spring XML命名空间）
            Element rootElement = doc.getRootElement();
            List<Element> beanElements = rootElement.elements("bean");

            // 4. 遍历解析每个bean标签
            if (beanElements.isEmpty()) {
                throw new RuntimeException("XML配置文件中未找到任何bean标签");
            }

            for (Element element : beanElements) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");

                if (id == null || className == null) {
                    throw new RuntimeException("bean标签必须包含id和class属性");
                }

                listBeans.add(new MyBean(id, className));
                System.out.println("✅ 解析到Bean：id=" + id + ", class=" + className);
            }
        } catch (DocumentException e) {
            throw new RuntimeException("解析XML失败", e);
        }
    }

    // 反射创建Bean实例（逻辑不变）
    private void createBeanInstances() {
        for (MyBean beanDef : listBeans) {
            try {
                Class<?> clazz = Class.forName(beanDef.getClazz());
                Object beanInstance = clazz.getDeclaredConstructor().newInstance();
                beanMap.put(beanDef.getId(), beanInstance);
                System.out.println("✅ 成功创建Bean：" + beanDef.getId() + " → " + beanInstance);
            } catch (Exception e) {
                throw new RuntimeException("创建Bean失败：" + beanDef.getId(), e);
            }
        }
    }

    @Override
    public Object getBean(String id) {
        Object bean = beanMap.get(id);
        if (bean == null) {
            throw new RuntimeException("未找到id为[" + id + "]的Bean，已加载的Bean：" + beanMap.keySet());
        }
        return bean;
    }
}