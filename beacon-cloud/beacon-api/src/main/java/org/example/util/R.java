package org.example.util;

import org.example.enums.ExceptionEnums;
import org.example.execption.ApiIllegalException;
import org.example.vo.ResultVO;

/**
 * R
 *
 * @author Yang QingBo
 * @date 2026-05-24 16:36
 * @description
 */

public class R {
	
	public static ResultVO ok(){
		ResultVO r = new ResultVO();
		r.setCode(0);
		r.setMsg("接收成功！");
		return r;
	}
	
	public static ResultVO error(Integer code,String msg) {
		ResultVO r = new ResultVO();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}
	
	public static ResultVO error(ApiIllegalException e) {
		ResultVO r = new ResultVO();
		r.setCode(e.getCode());
		r.setMsg(e.getMessage());
		return r;
	}
}
