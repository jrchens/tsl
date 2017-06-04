package me.simple.sys.controller;

import me.simple.domain.CurrentUser;
import me.simple.web.method.support.LoginedUser;
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
    public String indexPage(@LoginedUser CurrentUser currentUser, Model model) {
        // TODO dashboard
        return "index";
    }
}
