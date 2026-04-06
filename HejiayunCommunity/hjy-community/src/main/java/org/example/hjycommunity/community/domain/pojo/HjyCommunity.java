package org.example.hjycommunity.community.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * HjyCommunity
 * 小区实体类
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:08
 */

@Setter
@Getter
public class HjyCommunity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 384104666847271166L;
    /**
     * 小区id
     */
    @TableId
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long communityId;
    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 小区编码
     */
    private String communityCode;
    /**
     * 省区划码
     */
    private String communityProvinceCode;
    /**
     * 市区划码
     */
    private String communityCityCode;
    /**
     * 区县区划码
     */
    private String communityTownCode;
    /**
     * 详细地址
     */
    private String communityDetailedAddress;
    /**
     * 经度
     */
    private String communityLongitude;
    /**
     * 纬度
     */
    private String communityLatitude;
    /**
     * 物业id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long deptId;

    /**
     * 排序
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer communitySort;


}

