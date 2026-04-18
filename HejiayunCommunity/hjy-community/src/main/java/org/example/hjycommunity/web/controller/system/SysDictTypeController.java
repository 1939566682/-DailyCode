package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.constant.UserConstants;
import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.page.PageResult;
import org.example.hjycommunity.common.utils.SecurityUtils;
import org.example.hjycommunity.system.domain.pojo.SysDictType;
import org.example.hjycommunity.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SysDictTypeController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-16 14:29
 */

@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {
	
	@Autowired
	private SysDictTypeService sysDictTypeService;
	
	/**
	 * 多条件分页查询字典类型数据
	 */
	@GetMapping("/list")
	public PageResult list(SysDictType dictType) {
		startPage();
		List<SysDictType> list = sysDictTypeService.selectDictTypeList(dictType);
		return getPageResult(list);
	}
	
	/**
	 * 根据Id查询字典类型详细信息
	 */
	@GetMapping(value = "/{dictId}")
	public BaseResponse<SysDictType> getInfo(@PathVariable Long dictId) {
		return BaseResponse.success(sysDictTypeService.selectDictTypeById(dictId));
	}
	
	/**
	 * 新增字典类型
	 */
	@PostMapping
	public BaseResponse<Integer> add(@RequestBody SysDictType sysDictType) {
		
		if (UserConstants.NOT_UNIQUE.equals(sysDictTypeService.checkDictTypeUnique(sysDictType))) {
			return BaseResponse.fail("新增字典" + sysDictType.getDictName() + "失败,字典类型已经存在");
		}
		sysDictType.setCreateBy(SecurityUtils.getUserName());
		return toAjax(sysDictTypeService.insertDictType(sysDictType));
	}
	
	/**
	 * 修改字典类型
	 */
	@PutMapping
	public BaseResponse<Integer> edit(@RequestBody SysDictType sysDictType) {
		if (UserConstants.NOT_UNIQUE.equals(sysDictTypeService.checkDictTypeUnique(sysDictType))) {
			return BaseResponse.fail("修改字典" + sysDictType.getDictName() + "失败,字典类型已经存在");
		}
		sysDictType.setUpdateBy(SecurityUtils.getUserName());
		return toAjax(sysDictTypeService.updateDictType(sysDictType));
	}
	
	/**
	 * 删除字典类型
	 */
	@DeleteMapping("/{dictIds}")
	public BaseResponse<Integer> remove(@PathVariable Long[] dictIds) {
		return toAjax(sysDictTypeService.deleteDictTypeByIds(dictIds));
	}
	
	/**
	 * 清空缓存
	 */
	@DeleteMapping("/clearCache")
	public BaseResponse<String> clearCache() {
		sysDictTypeService.clearCache();
		return BaseResponse.success("清除缓存成功");
	}
}
