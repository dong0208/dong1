package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.controller.exception.ServiceException;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.service.TicketStoreService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/*
 *年票管理
 * @author 杨冬冬  
 * @date 2018/4/23   
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {


    @Autowired
    private TicketService ticketService;



    /**
     * 年票入库首页
     * @return
     */
    @GetMapping("/storage")
    public String home(Model model,
                       @RequestParam(required = false,defaultValue = "1",name = "p")
                        Integer pageNo){
        PageInfo<TicketInRecord> pageInfo = ticketService.findTicketRecordByPageNo(pageNo);
        model.addAttribute("pageInfo",pageInfo);
        return "ticket/storage/home";
    }
    /**
     * 新增入库
     */
    @GetMapping("/storage/new")
    public String newTicketStorage(Model model){
        String today= DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("today",today);
        return "ticket/storage/new";
    }
    @PostMapping("/storage/new")
    public String newTicketStorage(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes,Model model){

        try {
            ticketService.saveTicketInRecord(ticketInRecord);
            redirectAttributes.addFlashAttribute("message","新增成功");

        }catch (ServiceException ex){
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
            ex.getStackTrace();
        }

        return "redirect:/ticket/storage";

    }


}
