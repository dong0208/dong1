package com.dong.controller;

import com.dong.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/delete")
    public String deleteCustomer(){
        return "customer/delete";
    }
    @GetMapping("/new")
    public String Customer(){
        return "customer/new";
    }

    @PostMapping("/new")
    public String newCustomer(Customer customer,String level) {
        System.out.println("from value -> name:" + customer.getName()
                + "  address:" + customer.getAddress() + " level:" + level);
        return "redirect:/customer/new";
    }
    @GetMapping("/{id:\\d+}")
        public String viewCustomer(@PathVariable(name = "id") Integer customerId,
                Model model){
            System.out.println("----view customer:"+customerId);
            model.addAttribute("customerId",customerId);
            return "customer/view";
    }
    @GetMapping("/{typeName:d-\\d+}/{customerId:\\d+}")
    public ModelAndView viewCustomerByType(@PathVariable String typeName,
                                           @PathVariable Integer customerId) {
        System.out.println("---- typeName:" + typeName + "  customerId:" + customerId);
        ModelAndView modelAndView = new ModelAndView("customer/view");
        //设定传递的参数
        modelAndView.addObject("customerId",customerId);
        modelAndView.addObject("typeName",typeName);
        return modelAndView;
    }
    @GetMapping("/all.json")
    public @ResponseBody List<Customer> showAllCustmoter(){
        List<Customer> customers = Arrays.asList(
                new Customer(12,"网卡","西安"),
                new Customer(12,"辉辉","背景"),
                new Customer(12,"丹江口水库","河南")
        );
        return customers;
    }
    @GetMapping("/{id}.json")
    public @ResponseBody Customer viewsCustomer(@PathVariable Integer id){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setAddress("背景");
        customer.setName("黎明");
        return customer;
    }
    @GetMapping
    public String List(Model model,@RequestParam(name = "p",defaultValue = "1") Integer pageNo){
        System.out.println("pageNo:"+ pageNo);
       model.addAttribute("pageNo",pageNo);
        return "customer/list";
    }

}
