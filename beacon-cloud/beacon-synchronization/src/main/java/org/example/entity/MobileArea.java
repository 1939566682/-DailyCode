package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * 手机号区域表(MobileArea)实体类
 *
 * @author Yang QingBo
 * @since 2026-06-01 19:24:03
 */

@Getter
@Setter
public class MobileArea implements Serializable {
    private static final long serialVersionUID = -60646717918892808L;

    /**
     * 手机号前7位
     */
    private String mobileNumber;
/**
     * 手机号区域
     */
    private String mobileArea;
/**
     * 手机号运营商
     */
    private String mobileType;

}
