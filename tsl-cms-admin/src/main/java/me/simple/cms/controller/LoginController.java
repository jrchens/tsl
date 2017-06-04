package me.simple.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chensheng on 17/5/24.
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String login() {
        logger.info("into login page");
        // TODO auto login from session
        return "login";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public String login(String username, String password) {
        logger.info("process user login {}:{}", username, password);
        return "redirect:/index";
    }
}
