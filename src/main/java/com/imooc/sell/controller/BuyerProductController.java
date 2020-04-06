package com.imooc.sell.controller;

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVo;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 1:32
 * @Author: Mr.SU
 * @Description:买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
    * @Description: 查询买家商品
    * @param "[] "
    * @Return: com.imooc.sell.VO.ResultVo
    * @Author: Mr.Su
    * @Date: 2020/4/6 0006 2:48
    */
    @GetMapping("/list")
    public ResultVo list() throws Exception{

        //1、查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2、查询类目（一次性查询） 1>传统方法
        /*List<Integer> productCategoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo: productInfoList) {
            productCategoryTypeList.add(productInfo.getCategoryType());
        }*/

        //2> 精简方法（Java8新特性，lambda表达式） 推荐使用
        List<Integer> productCategoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList =  productCategoryService.findByCategoryTypeIn(productCategoryTypeList);

        //3、数据拼装


        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory productCategory: productCategoryList) {
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            for (ProductInfo productInfo:productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    /*productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());*/

                    //使用这个方法替代上面，一个类里面的所有属性，赋值到另一个类的相同属性
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVoUtil.sucess(productVOList);
    }





}
