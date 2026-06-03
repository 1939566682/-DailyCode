package org.example.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 手机号黑名单表(MobileBlack)实体类
 *
 * @author Yang QingBo
 * @since 2026-06-03 19:56:31
 */

@Data
public class MobileBlack implements Serializable {
    
    private static final long serialVersionUID = -90859437396642030L;
    
/**
     * 黑名单手机号
     */
    private String blackNumber;
    
/**
     * 黑名单类型 0-全局黑名单  其他-客户黑名单
     */
    private Integer clientId;

}
