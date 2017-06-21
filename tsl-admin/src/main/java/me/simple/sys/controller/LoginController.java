package me.simple.sys.controller;

import me.simple.domain.*;
import me.simple.sys.service.SysMenuService;
import me.simple.sys.service.SysUserService;
import me.simple.web.method.support.LoginedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * Created by chensheng on 17/5/24.
 */

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String index(LoginUser loginUser, BindingResult bindingResult, Model model) {
        logger.info("into login page");
        // TODO auto login from session
        model.addAttribute(loginUser);
        return "login";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public String login(
            LoginUser loginUser, BindingResult bindingResult,
            WebRequest webRequest,
            Model model) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        logger.info("process user login {}:{}", username, password);
        SysUser sysUser = sysUserService.getByUsername(username);
        if(sysUser == null){
            bindingResult.rejectValue("username","username.not.exists",new Object[]{username},"username.not.exists");
            return "login";
        }
        if (!password.equalsIgnoreCase(sysUser.getPassword())){
            bindingResult.rejectValue("password","password.not.eq",new Object[]{},"password.not.eq");
            return "login";
        }
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(sysUser.getId());
        currentUser.setUsername(username);
        currentUser.setViewname(sysUser.getViewname());
        webRequest.setAttribute("currentUser",currentUser, RequestAttributes.SCOPE_SESSION);

        SysMenu root = sysMenuService.getRoot();
        List<SysMenu> sysMenuGroupList = sysMenuService.getChildrenByPid(root.getId());
        for (SysMenu smg: sysMenuGroupList
             ) {
            smg.setChildren(sysMenuService.getChildrenByPid(smg.getId()));
        }
        webRequest.setAttribute("sysMenuGroupList",sysMenuGroupList,RequestAttributes.SCOPE_SESSION);

        return "redirect:/index";
    }



    @RequestMapping(value = {"logout"}, method = RequestMethod.GET)
    public String logout(
            /*LoginUser loginUser, BindingResult bindingResult,*/
            @LoginedUser CurrentUser currentUser,
            WebRequest webRequest,
            Model model) {
        logger.info("process user ({}) logout", currentUser.getUsername());
        webRequest.removeAttribute("currentUser", RequestAttributes.SCOPE_SESSION);
        return "redirect:/";
    }
}
