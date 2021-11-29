package com.example.form_email.controller;


import com.example.form_email.Service.MailService;


import com.example.form_email.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class MyController {

    @Autowired
    private MailService notificationService;
////
//    @Autowired
//    private User user;


    /**
     * @return
     */
    @GetMapping("/addUser")
    public String sendForm(User user) {

        return "addUser";
    }

    @PostMapping("/addUser")
    public String processForm(User user) {

        return "showMessage";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user) {
        System.out.println("user from ui=" + user);
        try {
            notificationService.sendEmailWithAttachment(user);
        } catch (MailException | MessagingException mailException) {
            System.out.println(mailException);
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("showMessage");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

}