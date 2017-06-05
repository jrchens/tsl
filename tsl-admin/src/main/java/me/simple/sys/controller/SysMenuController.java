package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysMenu;
import me.simple.sys.service.SysMenuService;
import me.simple.validator.group.*;
import me.simple.web.method.support.LoginedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys_menu")
public class SysMenuController {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysMenu);
        return "sys_menu/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysMenu);
        return "sys_menu/create";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Validated(value = {Save.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_menu/create";
        }
        sysMenuService.save(sysMenu, currentUser);
        return "redirect:/sys_menu/index";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@Validated(value = {Remove.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_menu/index";
        }
        sysMenuService.remove(sysMenu, currentUser);
        return "redirect:/sys_menu/index";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_menu/index";
        }
        model.addAttribute(sysMenuService.get(sysMenu, currentUser));
        return "sys_menu/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Validated(value = {Update.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_menu/edit";
        }
        sysMenuService.update(sysMenu, currentUser);
        return "redirect:/sys_menu/index";
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysMenu sysMenu, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysMenu> rows = sysMenuService.query(sysMenu, pageable, currentUser);
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SysMenu> get(@Validated(value = {Get.class}) SysMenu sysMenu, BindingResult bindingResult,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        SysMenu data = sysMenuService.get(sysMenu,currentUser);

        return ResponseEntity.ok(data);
    }
}
