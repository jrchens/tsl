package me.simple.sys.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysGroup;
import me.simple.domain.SysUserGroup;
import me.simple.sys.service.SysGroupService;
import me.simple.sys.service.SysUserGroupService;
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
@RequestMapping("sys_user_group")
public class SysUserGroupController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserGroupController.class);

    @Autowired
    private SysUserGroupService sysUserGroupService;

    @Autowired
    private SysGroupService sysGroupService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysUserGroup);
        return "sys_user_group/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysUserGroup);
        return "sys_user_group/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_user_group/create";
//        }
//        sysUserGroupService.save(sysUserGroup, currentUser);
//        return "redirect:/sys_user_group/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
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
            sysUserGroupService.save(sysUserGroup, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_user_group/index";
//        }
//        sysUserGroupService.remove(sysUserGroup, currentUser);
//        return "redirect:/sys_user_group/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(/*@Validated(value = {Remove.class}) */SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
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
            sysUserGroupService.remove(sysUserGroup, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_user_group/index";
        }
        model.addAttribute(sysUserGroupService.get(sysUserGroup, currentUser));
        return "sys_user_group/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_user_group/edit";
//        }
//        sysUserGroupService.update(sysUserGroup, currentUser);
//        return "redirect:/sys_user_group/index";
//    }


    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysUserGroup sysUserGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
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
             sysUserGroupService.update( sysUserGroup, currentUser);
        }
    
        body.put("success",success);
        body.put("data",data);
        body.put("message",message);
    
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysUserGroup sysUserGroup, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysUserGroup> rows = sysUserGroupService.query(sysUserGroup, pageable, currentUser);
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }



    @RequestMapping(value = "query", method = RequestMethod.GET,params = {"t=u"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> queryByUid(
            @Validated(value = {Query.class}) SysUserGroup sysUserGroup,
            BindingResult bindingResult,
            Pageable pageable,
            @LoginedUser CurrentUser currentUser, Model model) {

        List<SysGroup> sysGroupList = sysGroupService.queryAll(true);
        List<SysUserGroup> sysUserGroupList = sysUserGroupService.queryByUid(sysUserGroup.getUid(),true);

        List<SysGroup> rows = Lists.newArrayList();
        for (SysGroup sysGroup: sysGroupList
                ) {
            if(sysUserGroup.getGid() == sysGroup.getId()){
                continue;
            }
            for (SysUserGroup userGroup: sysUserGroupList
                    ) {
                if(sysGroup.getId() == userGroup.getGid()){
                    sysGroup.setChecked(true);
                    break;
                }
            }
            rows.add(sysGroup);
        }

        model.addAttribute(sysGroupList);


        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", rows.size());

        return ResponseEntity.ok(body);
    }

}
