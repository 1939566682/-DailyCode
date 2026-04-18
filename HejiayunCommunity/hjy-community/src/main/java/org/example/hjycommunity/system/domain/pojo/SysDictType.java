package org.example.hjycommunity.system.domain.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.example.hjycommunity.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;

/**
 * SysDictType
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-16 14:20
 */

public class SysDictType extends BaseEntity {
	
	@Serial
	private static final long serialVersionUID = -5340429290026509016L;
	
	/**
	 * 字典主键
	 */
	@Excel(name = "字典主键")
	@TableId
	private Long dictId;
	
	/**
	 * 字典名称
	 */
	@Excel(name = "字典名称")
	private String dictName;
	
	/**
	 * 字典类型
	 */
	@Excel(name = "字典类型")
	private String dictType;
	
	/**
	 * 状态（0正常 1停用）
	 */
	@Excel(name = "状态", replace = {"正常_0", "停用_1"})
	private String status;
	
	private String beginTime;
	private String endTime;
	
	private String remark;
	
	@Override
	public String getRemark() {
		return remark;
	}
	
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getDictId() {
		return dictId;
	}
	
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	
	@NotBlank(message = "字典名称不能为空")
	@Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
	public String getDictName() {
		return dictName;
	}
	
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	@NotBlank(message = "字典类型不能为空")
	@Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
	public String getDictType() {
		return dictType;
	}
	
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("dictId", getDictId())
				.append("dictName", getDictName())
				.append("dictType", getDictType())
				.append("status", getStatus())
				.append("createBy", getCreateBy())
				.append("createTime", getCreateTime())
				.append("updateBy", getUpdateBy())
				.append("updateTime", getUpdateTime())
				.append("remark", getRemark())
				.toString();
	}
}
