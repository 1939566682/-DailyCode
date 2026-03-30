package org.example.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 08:41
 */

@ControllerAdvice
public class ExceptionHandler01 {

    @ExceptionHandler(value = java.lang.ArithmeticException.class)
    public String MyException(){
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        return "myError";

    }

}
