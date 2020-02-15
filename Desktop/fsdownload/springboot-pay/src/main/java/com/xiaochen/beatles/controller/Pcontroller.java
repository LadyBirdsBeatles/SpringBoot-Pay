package com.xiaochen.beatles.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Pcontroller {
    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/getdate")
    String data() {
        return "redirect:/statics/html/data.html";
    }

    @RequestMapping("/getpays")
    String getpays() {
        return "redirect:/statics/html/pays.jsp";
    }

    @RequestMapping("/getindex")
    String gathtml() {
        return "redirect:index.jsp";
    }

    @RequestMapping("/gatjspmoth")
    String gatjsp() {
        return "getmoth";
    }

    @RequestMapping("/return_url")
    String returns() {
        return "return_url";
    }

    @RequestMapping("/notify_url")
    String notifys() {
        return "notify_url";
    }
}
