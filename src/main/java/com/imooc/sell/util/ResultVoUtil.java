package com.imooc.sell.util;

import com.imooc.sell.VO.ResultVo;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 2:45
 * @Author: Mr.SU
 * @Description:
 */
public class ResultVoUtil {

    /**
    * @Description: 成功
    * @param "[object] "
    * @Return: com.imooc.sell.VO.ResultVo
    * @Author: Mr.Su
    * @Date: 2020/4/6 0006 2:47
    */
    public static ResultVo sucess(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }

    public static ResultVo sucess(){
        return sucess(null);
    }

    /**
     * @Description: 失败
     * @param "[object] "
     * @Return: com.imooc.sell.VO.ResultVo
     * @Author: Mr.Su
     * @Date: 2020/4/6 0006 2:47
     */
    public static ResultVo fail(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

}
