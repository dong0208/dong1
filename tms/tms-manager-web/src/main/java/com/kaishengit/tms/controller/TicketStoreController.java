package com.kaishengit.tms.controller;

import com.kaishengit.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*   
 *售票点管理
 * @author 杨冬冬  
 * @date 2018/4/20   
 */
@Controller
@RequestMapping("/ticketstore")
public class TicketStoreController {

    @Autowired
    private TicketStoreService ticketStoreService;

    @GetMapping
    public String home(){

        return "store/home";
    }
    @GetMapping("/new")
    public String newStore(){
        return "";
    }
}
