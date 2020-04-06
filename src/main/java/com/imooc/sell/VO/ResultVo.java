package com.imooc.sell.VO;

import lombok.Data;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 1:36
 * @Author: Mr.SU
 * @Description:http请求返回的最外层对象
 */
@Data
public class ResultVo<T> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*返回的具体内容*/
    private T data;

}
