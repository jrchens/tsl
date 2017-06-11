package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysUser;
import me.simple.sys.service.SysUserService;
import me.simple.validator.group.*;
import me.simple.web.method.support.LoginedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

/**
 * Created by chensheng on 17/5/28.
 */
@Controller
@RequestMapping("sys_user")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysUser);
        return "sys_user/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysUser);
        return "sys_user/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//        // username exists
//        if (sysUserService.getByUsername(sysUser.getUsername()) != null) {
//            bindingResult.rejectValue("username", "value.exists", new Object[]{sysUser.getUsername()}, "value.exists");
//        }
//        if (bindingResult.hasErrors()) {
//            return "sys_user/create";
//        }
//        sysUserService.save(sysUser, currentUser);
//        return "redirect:/sys_user/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        // username exists
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();
//        if (sysUserService.getByUsername(sysUser.getUsername()) != null) {
//            bindingResult.rejectValue("username", "value.exists", new Object[]{sysUser.getUsername()}, "value.exists");
//        }

//        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            success = false;
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//                data.put(fe.getField(),messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            message = "valid.failed";
        }else{
            sysUserService.save(sysUser, currentUser);
        }

        body.put("success",success);
//        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_user/index";
//        }
//        sysUserService.remove(sysUser, currentUser);
//        return "redirect:/sys_user/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(@Validated(value = {Remove.class})SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        boolean success = true;
        String message = "remove.success";
        Map<String,Object> body = Maps.newHashMap();

//        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            success = false;
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//                data.put(fe.getField(),messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            message = "valid.failed";
        }else{
            sysUserService.remove(sysUser, currentUser);
        }

        body.put("success",success);
//        body.put("data",data);
        body.put("message",messageSource.getMessage(message,null,null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        if (bindingResult.hasErrors()) {
            return "sys_user/index";
        }
        model.addAttribute(sysUserService.get(sysUser, currentUser));
        return "sys_user/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_user/edit";
//        }
//        sysUserService.update(sysUser, currentUser);
//        return "redirect:/sys_user/index";
//    }

    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysUser sysUser, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        boolean success = true;
        String message = "update.success";
        Map<String,Object> body = Maps.newHashMap();
//        Map<String,Object> data = Maps.newHashMap();

        if (bindingResult.hasErrors()) {
            success = false;
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//                data.put(fe.getField(),messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            message = "valid.failed";
        }else{
            sysUserService.update(sysUser, currentUser);
        }

        body.put("success",success);
//        body.put("data",data);
        body.put("message",messageSource.getMessage(message,null,null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysUser sysUser, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysUser> rows = sysUserService.query(sysUser, pageable, currentUser);
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }
}
