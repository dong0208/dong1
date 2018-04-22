package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.fileStore.QiniuStore;
import com.kaishengit.tms.service.TicketStoreService;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/ticketstore")
public class TicketStoreController {

    @Autowired
    private TicketStoreService ticketStoreService;
    @Autowired
    private QiniuStore qiniuStore;

    @GetMapping
    public String home(Model model,
                       @RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false,defaultValue = "") String storeName,
                       @RequestParam(required = false,defaultValue = "") String storeManager,
                       @RequestParam(required = false,defaultValue = "") String storeTel) {
        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("storeName",storeName);
        queryParam.put("storeManager",storeManager);
        queryParam.put("storeTel",storeTel);

        PageInfo<TicketStore> pageInfo = ticketStoreService.findAllTicketStoreByPage(pageNo,queryParam);
        model.addAttribute("pageInfo",pageInfo);
        return "store/home";
    }

    /**
     * ������Ʊ��
     * @return
     */
    @GetMapping("/new")
    public String newStore(Model model) {
        //��ȡ��ţ�ļ��ϴ�token
        String upToken = qiniuStore.getUploadToken();
        model.addAttribute("upToken",upToken);
        return "store/new";
    }

    @PostMapping("/new")
    public String newStore(TicketStore ticketStore, RedirectAttributes redirectAttributes) {
        ticketStoreService.saveNewTicktStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","�����ɹ�");
        return "redirect:/ticketstore";
    }

    /**
     * �鿴��Ʊ������
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String viewTicketStore(@PathVariable Integer id,Model model) {
        TicketStore ticketStore = ticketStoreService.findTicketStoreById(id);

        if(ticketStore == null) {
            throw new NotFoundException();
        }
        //���ҹ�������Ʊ���˺�
        StoreAccount storeAccount = ticketStoreService.findStoreAccountById(ticketStore.getId());

        model.addAttribute("storeAccount",storeAccount);
        model.addAttribute("ticketStore",ticketStore);
        return "store/info";
    }

    /**
     * �༭��Ʊ��
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String editTicketStore(@PathVariable Integer id,Model model) {
        TicketStore ticketStore = ticketStoreService.findTicketStoreById(id);
        if(ticketStore == null) {
            throw new NotFoundException();
        }

        //��ȡ��ţ�ϴ���Token
        String uploadToken = qiniuStore.getUploadToken();

        model.addAttribute("uploadToken",uploadToken);
        model.addAttribute("ticketStore",ticketStore);
        return "store/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateTicketStore(TicketStore ticketStore,RedirectAttributes redirectAttributes) {
        ticketStoreService.updateTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","�޸ĳɹ�");
        return "redirect:/ticketstore";
    }
}