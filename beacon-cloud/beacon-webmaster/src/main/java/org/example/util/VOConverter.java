package org.example.util;

import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * VOConverter - Entity与VO之间的转换工具类
 * 同名字段自动映射（BeanUtils.copyProperties），差异字段在各ServiceImpl中手动补充
 */
public class VOConverter {

    /**
     * 单个 Entity → VO 转换（同名字段自动映射）
     */
    public static <E, V> V toVO(E entity, Class<V> voClass) {
        if (entity == null) return null;
        try {
            V vo = voClass.newInstance();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        } catch (Exception e) {
            throw new RuntimeException("VO转换失败: " + entity.getClass().getName() + " → " + voClass.getName(), e);
        }
    }

    /**
     * 批量 Entity → VO 转换
     */
    public static <E, V> List<V> toVOList(List<E> entities, Class<V> voClass) {
        if (entities == null) return null;
        List<V> voList = new ArrayList<>();
        for (E entity : entities) {
            voList.add(toVO(entity, voClass));
        }
        return voList;
    }

    /**
     * VO → Entity 转换（同名字段自动映射）
     */
    public static <V, E> E toEntity(V vo, Class<E> entityClass) {
        if (vo == null) return null;
        try {
            E entity = entityClass.newInstance();
            BeanUtils.copyProperties(vo, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Entity转换失败: " + vo.getClass().getName() + " → " + entityClass.getName(), e);
        }
    }
}
