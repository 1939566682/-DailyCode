package org.example.hjycommunity.community.domain.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * HjyCommunityExcelDto
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-07 16:38
 */

@Data
@ExcelTarget("community")
public class HjyCommunityExcelDto implements Serializable {


    @Serial
    private static final long serialVersionUID = 5593642564091103159L;

    /**
     * 小区id
     */
    @Excel(name = "序号")
    private Long communityId;

    /**
     * 小区名称
     */
    @Excel(name = "小区名称")
    private String communityName;

    /**
     * 小区编码
     */
    @Excel(name = "小区编码")
    private String communityCode;

    /**
     * 省
     */
    @Excel(name = "省")
    private String communityProvinceName;

    /**
     * 市
     */
    @Excel(name = "市")
    private String communityCityName;

    /**
     * 区
     */
    @Excel(name = "区/县")
    private String communityTownName;

    /**
     * 创建时间
     */

    @Excel(name = "创建时间", exportFormat = "yyyy年MM月dd日")
    private Date createTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}