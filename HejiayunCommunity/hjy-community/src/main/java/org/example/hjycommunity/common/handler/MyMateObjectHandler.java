package org.example.hjycommunity.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyMateObjectHandler
 * 自定义填充控制器
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 22:38
 */

@Component
public class MyMateObjectHandler
        implements MetaObjectHandler {

    // INSERT 时要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        // 根据属性名称设置需要填充的字段
        this.strictInsertFill(metaObject, "createBy", String.class, "admin");
        this.strictInsertFill(metaObject, "updateBy", String.class, "admin");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }

    // UPDATE 时要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        // 根据属性名称设置需要填充的字段
        this.strictUpdateFill(metaObject, "updateBy", String.class, "admin");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
