package com.dong.controller;

import com.dong.entity.Product;
import com.dong.entity.ProductType;
import com.dong.exception.NotFoundException;
import com.dong.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.RelativeRedirectFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/new")
        public String newProduct(Model model){
        //��ѯ������Ʒ������
        List<ProductType> productTypeList = productService.findAllProductType();

        model.addAttribute("typeList",productTypeList);
        return "product/new";
        }
    @PostMapping("/new")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){
        //����Ʒ���浽���ݿ���
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("message","�����Ʒ�ɹ�");
        return "redirect:/product";
    }
    @GetMapping
    public String list(@RequestParam(defaultValue = "1",name = "p",required = false)Integer pageNo,
                        @RequestParam(required = false)String productName,
                        @RequestParam(required = false)String place,
                        @RequestParam(required = false)Float minPrice,
                        @RequestParam(required = false)Float maxPrice,
                        @RequestParam(required = false)Integer typeId,
                       Model model){
        Map<String,Object> queryParamMap = new HashMap<>();
        queryParamMap.put("productName",productName);
        queryParamMap.put("place",place);
        queryParamMap.put("minPrice",minPrice);
        queryParamMap.put("maxPrice",maxPrice);
        queryParamMap.put("typeId",typeId);
        PageInfo<Product> pageInfo = productService.findAllProductByPageNoAndQueryParam(pageNo,queryParamMap);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeList",productService.findAllProductType());

        return "product/list";
    }
    @GetMapping("/{id:\\d+}")
    public String viewProduct(@PathVariable Integer id , Model model){
        Product product = productService.findById(id);
        if(product == null){
            throw new NotFoundException();
        }
        model.addAttribute("product",product);
        return "product/product";
    }
    @GetMapping("/{id:\\d+}/edit")
    public String editProduct(@PathVariable Integer id ,Model model){
        //����ID������Ʒ
        Product product = productService.findById(id);
        if(product == null){
            throw new NotFoundException();
        }
        //��ѯ��Ʒ�����б�
        List<ProductType> productTypeList = productService.findAllProductType();
        model.addAttribute("typeList",productTypeList);
        model.addAttribute("product",product);
        return "product/edit";
    }
   @PostMapping("/{id:\\d+}/edit")
    public String editProduct(Product product, RedirectAttributes redirectAttributes){
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message","��Ʒ�޸ĳɹ�");
        return "redirect:/product";
    }
    @GetMapping("/{id:\\d+}/del")
    public String delProduct(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        System.out.println("ProductController.delProduct");
        System.out.println("id = " + id);
        productService.delProductById(id);
        redirectAttributes.addFlashAttribute("message","ɾ���ɹ�");
        return "redirect:/product";
    }
}
