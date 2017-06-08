package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysRole;
import me.simple.sys.service.SysRoleService;
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
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sys_role")
public class SysRoleController {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysRole);
        return "sys_role/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysRole);
        return "sys_role/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_role/create";
//        }
//        sysRoleService.save(sysRole, currentUser);
//        return "redirect:/sys_role/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();

        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            success = false;
            List<FieldError> feList = bindingResult.getFieldErrors();
            for (FieldError fe: feList) {
                data.put(fe.getField(),
                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
            }
        }else{
            sysRoleService.save(sysRole, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_role/index";
//        }
//        sysRoleService.remove(sysRole, currentUser);
//        return "redirect:/sys_role/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(/*@Validated(value = {Remove.class}) */SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();

        Map<String,Object> data = Maps.newHashMap();

        if (bindingResult.hasErrors()) {
            success = false;
            List<FieldError> feList = bindingResult.getFieldErrors();
            for (FieldError fe: feList) {
                data.put(fe.getField(),
                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
            }
        }else{
            sysRoleService.remove(sysRole, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_role/index";
        }
        model.addAttribute(sysRoleService.get(sysRole, currentUser));
        return "sys_role/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_role/edit";
//        }
//        sysRoleService.update(sysRole, currentUser);
//        return "redirect:/sys_role/index";
//    }


    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysRole sysRole, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();

        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            List<FieldError> feList = bindingResult.getFieldErrors();
            for (FieldError fe: feList) {
                data.put(fe.getField(),
                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
            }
            success = false;
        }else{
             sysRoleService.update( sysRole, currentUser);
        }
    
        body.put("success",success);
        body.put("data",data);
        body.put("message",message);
    
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysRole sysRole, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysRole> rows = sysRoleService.query(sysRole, pageable, currentUser);
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }
}
