package org.example.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.client.BeaconCacheClient;
import org.example.constant.ApiConstant;
import org.example.constant.CacheConstant;
import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.filter.CheckFilter;
import org.example.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * TemplateCheckFilter
 *
 * @author Yang QingBo
 * @date 2026-05-24 15:33
 * @description 校验短信的模板
 */

@Slf4j
@Service("template")
public class TemplateCheckFilter implements CheckFilter {
	
	@Autowired
	private BeaconCacheClient beaconCacheClient;
	
	private final String TEMPLATE_TEXT = "templateText";
	
	private final String TEMPLATE_PLACEHOLDER = "#";
	
	@Override
	public void check(StandardSubmit submit) {
		log.info("【接口模块 - 校验模板】  校验ing......");
		
		// 从 submit 中获取到短信内容，签名信息，签名 id
		String text = submit.getText();
		log.info("【接口模块 - 校验模板】  短信 text = {}", text);
		String sign = submit.getSign();
		Long signId = submit.getSignId();
		
		// 将短信内容中的签名直接去掉，获取短信具体内容
		text = text.replace(ApiConstant.SIGN_PREFIX + sign + ApiConstant.SIGN_SUFFIX, "");
		log.info("【接口模块 - 校验模板】  去签名后短信 text = {}", text);
		
		// 从缓存中获取到签名 id 绑定的所有模板
		Set<Map> templates = beaconCacheClient.sMember(CacheConstant.CLIENT_TEMPLATE + signId);
		log.info("【接口模块 - 校验模板】  签名ID：{} 所绑定的所有模板 = {}", signId, templates);
		
		// 遍历签名绑定的所有模板信息
		if (templates != null && !templates.isEmpty()) {
			for (Map template : templates) {
				// 将模板内容和短信具体内容做匹配 - true - 匹配成功
				String templateText = template.get(TEMPLATE_TEXT).toString();
				if (text.equals(templateText)) { // 无变量模版匹配
					log.info("【接口模块 - 校验模板】  校验通过 template = {}", templateText);
					return;
				}
				// 判断模板中是否只包含一个变量，如果是，直接让具体短信内容匹配前缀和后缀
				if (templateText != null && templateText.contains(TEMPLATE_PLACEHOLDER)
						&& templateText.length() - templateText.replaceAll(TEMPLATE_PLACEHOLDER, "").length() == 2) {
					// 获取模板撇去占位符#之后的前缀和后缀
					String templateTextPrefix = templateText.substring(0, templateText.indexOf(TEMPLATE_PLACEHOLDER));
					String templateTextSuffix = templateText.substring(templateText.lastIndexOf(TEMPLATE_PLACEHOLDER) + 1);
					if (text.startsWith(templateTextPrefix) && text.endsWith(templateTextSuffix)) {
						log.info("【接口模块 - 校验模板】  校验通过 template = {}", templateText);
						return;
					}
				}
			}
		}
		// 模板校验失败
		log.info("【接口模块 - 校验模板】  无可用模板 text = {}", text);
		
		throw new ApiIllegalException(ExceptionEnums.NO_AVAILABLE_TEMPLATE);
	}
	
}
