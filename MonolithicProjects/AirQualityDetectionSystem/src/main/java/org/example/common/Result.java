package org.example.common;

import org.example.vo.ResultVO;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:19
 */

public class Result {

    public static <T> ResultVO<T> success(){
        return new ResultVO<>(200,"success",null);
    }

    public static <T> ResultVO<T> success(T data){
        return new ResultVO<>(200,"success",data);
    }

    public static <T> ResultVO<T> fail(String msg){
        return new ResultVO<>(500,msg,null);
    }

    public static <T> ResultVO<T> fail(int code,String msg){
        return new ResultVO<>(code,msg,null);
    }
}
