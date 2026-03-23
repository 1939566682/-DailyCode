package org.example.service.impl;

import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 18:21
 */


/**
 *  @Component注解的作用：简化了applicationContext.xml中对这个创建对象的配置 ，而创建对象这件事还是spring来管理。
 *  帮我们构建对象，默认的名字就是类名的首字母小写： UserServiceImpl  ---> userServiceImpl
 *  我们也可以指定对象的名字：通过传入参数的形式：@Component("usi")
 */
@Service
public class UserServiceImpl implements UserService {
    /*
     * 加入@Autowired注解的作用：帮我们在创建对象以后完成属性的注入，此时我们不需要提供setter方法
     * 注入形式：先按照类型从spring容器中去找匹配的对象注入进来。
     * 如果容器中存在多个相同类型的对象，那么就按照名字去找。
     * 比如容器中UserMapper实现类对象1 ：um1   还有UserMapper实现类对象2：um2
     * 此时就需要搭配另一个注解：@Qualifier("um1")  来指定你需要的那个对象
     * 使用了 @Qualifier("um1")注解后@Autowired是不能省略的，因为@Autowired帮我们完成属性注入，
     * @Qualifier只是定位到你需要注入的对象，必须搭配使用
     * */
//    @Autowired
    private UserMapper userMapper;
    /*加入@Autowired注解以后，底层会自动帮我们定义setter方法，无需我们自己创建这个方法*/
    //todo  必须有这个  @Autowired 自动装配不生效  并且需要去掉 @Autowired  暂不知道原因
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectOneUser(String uname, String pwd) {
        /*ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper)ac.getBean("userMapper");*/
        return userMapper.selectOneUser(uname,pwd);
    }

    /*public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String name:beanDefinitionNames){
            System.out.println(name);
        }
    }*/
}
