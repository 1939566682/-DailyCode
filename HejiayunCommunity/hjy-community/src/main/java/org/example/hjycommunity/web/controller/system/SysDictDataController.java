package org.example.hjycommunity.web.controller.system;

import org.example.hjycommunity.common.core.controller.BaseController;
import org.example.hjycommunity.common.core.domain.BaseResponse;
import org.example.hjycommunity.common.core.page.PageResult;
import org.example.hjycommunity.common.utils.SecurityUtils;
import org.example.hjycommunity.system.domain.pojo.SysDictData;
import org.example.hjycommunity.system.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SysDictDataController
 *
 * @author Yang QingBo
 * {@code @date} 2026-04-15 20:33
 */

@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
	
	@Autowired
	private SysDictDataService sysDictDataService;
	
	/**
	 * 根据条件分页查询字典数据
	 *
	 * @param sysDictData 字典数据信息
	 * @return 字典数据集合信息
	 */
	@RequestMapping("/list")
	public PageResult selectDictDataList(SysDictData sysDictData) {
		startPage();
		List<SysDictData> dictDataList = sysDictDataService.selectDictDataList(sysDictData);
		return getPageResult(dictDataList);
	}
	
	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 *
	 * @param dictType  字典类型
	 * @param dictValue 字典键值
	 * @return 字典标签
	 */
	@GetMapping("/xxx") // todo 未知访问路径
	public BaseResponse<String> selectDictLabel(String dictType, String dictValue) {
		return BaseResponse.success(sysDictDataService.selectDictLabel(dictType, dictValue));
	}
	
	/**
	 * 根据Id查询字典详细信息
	 */
	@GetMapping(value = "/{dictCode}")
	public BaseResponse<SysDictData> getInfo(@PathVariable Long dictCode){
		return BaseResponse.success(sysDictDataService.selectDictDataById(dictCode));
	}
	
	/**
	 * 根据字典类型查询字典数据信息
	 */
	@GetMapping(value = "/type/{dictType}")
	public BaseResponse<List<SysDictData>> getInfo(@PathVariable String dictType){
		
		return BaseResponse.success(sysDictDataService.selectDictDataByType(dictType));
	}
	
	/**
	 * 新增字典数据
	 */
	@PostMapping
	public BaseResponse<Integer> add(@RequestBody SysDictData sysDictData){
		sysDictData.setCreateBy(SecurityUtils.getUserName());
		return toAjax(sysDictDataService.insertDictData(sysDictData));
	}
	
	/**
	 * 修改字典数据
	 */
	@PutMapping
	public BaseResponse<Integer> edit(@RequestBody SysDictData sysDictData){
		sysDictData.setUpdateBy(SecurityUtils.getUserName());
		return toAjax(sysDictDataService.updateDictData(sysDictData));
	}
	
	/**
	 * 删除字典数据
	 */
	@DeleteMapping("/{dictCodes}")
	public BaseResponse<Integer> remove(@PathVariable Long[] dictCodes){
		
		return toAjax(sysDictDataService.deleteDictDataByIds(dictCodes));
	}

}
