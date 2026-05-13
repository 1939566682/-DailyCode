package org.example.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * SentinelOriginParser
 *
 * @author Yang QingBo
 * {@code @date} 2026-05-13 11:30
 */

@Component
public class SentinelOriginParser implements RequestOriginParser {
	@Override
	public String parseOrigin(HttpServletRequest request) {
		String origin = request.getHeader("origin");
		if (StringUtils.isEmpty(origin)) {
			origin = "";
		}
		return origin;
	}
}
