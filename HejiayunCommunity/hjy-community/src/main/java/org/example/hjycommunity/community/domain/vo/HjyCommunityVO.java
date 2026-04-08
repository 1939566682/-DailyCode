package org.example.hjycommunity.community.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * HjyCommunityVO
 * 小区数据传输对象
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-06 18:18
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class HjyCommunityVO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3605024356583411238L;
    /**
     * 小区id
     */
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
     * 省区名称
     */
    private String communityProvinceName;
    /**
     * 市区划码
     */
    private String communityCityCode;
    /**
     * 市区名称
     */
    private String communityCityName;
    /**
     * 区县区划码
     */
    private String communityTownCode;
    /**
     * 区县区名称
     */
    private String communityTownName;
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
    private Long deptId;
    /**
     * 排序
     */
    private Integer communitySort;


}
