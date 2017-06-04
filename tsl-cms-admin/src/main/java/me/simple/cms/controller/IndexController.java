package me.simple.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chensheng on 17/5/25.
 */

@Controller
public class IndexController {


    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("viewname", "Viewname");
        return "index";
    }
}
