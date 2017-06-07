package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysGroup;
import me.simple.sys.service.SysGroupService;
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
@RequestMapping("sys_group")
public class SysGroupController {
    private static final Logger logger = LoggerFactory.getLogger(SysGroupController.class);

    @Autowired
    private SysGroupService sysGroupService;


    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysGroup);
        return "sys_group/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysGroup);
        return "sys_group/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_group/create";
//        }
//        sysGroupService.save(sysGroup, currentUser);
//        return "redirect:/sys_group/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();

        String groupname = sysGroup.getGroupname();
        if (sysGroupService.getByGroupname(groupname) != null) {
            bindingResult.rejectValue("groupname", "value.exists", new Object[]{groupname}, "value.exists");
        }

        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            success = false;
            List<FieldError> feList = bindingResult.getFieldErrors();
            for (FieldError fe: feList
                 ) {
                data.put(fe.getField(),
                        messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
            }

        }else{
            sysGroupService.save(sysGroup, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_group/index";
//        }
//        sysGroupService.remove(sysGroup, currentUser);
//        return "redirect:/sys_group/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(/*@Validated(value = {Remove.class}) */SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();

        if (bindingResult.hasErrors()) {
            success = false;
        }else{
            sysGroupService.remove(sysGroup, currentUser);
        }

        body.put("success",success);
        body.put("message",message);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_group/index";
        }
        model.addAttribute(sysGroupService.get(sysGroup, currentUser));
        return "sys_group/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_group/edit";
//        }
//        sysGroupService.update(sysGroup, currentUser);
//        return "redirect:/sys_group/index";
//    }


    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysGroup sysGroup, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = null;
        Map<String,Object> body = Maps.newHashMap();
        
        if (bindingResult.hasErrors()) {
            List<FieldError> fes = bindingResult.getFieldErrors();
    
            for (FieldError fe: fes
            ) {
            fe.getField();
            fe.getRejectedValue();
            }
            success = false;
        }else{
             sysGroupService.update( sysGroup, currentUser);
        }
    
        body.put("success",success);
        body.put("message",message);
    
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysGroup sysGroup, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysGroup> rows = sysGroupService.query(sysGroup, pageable, currentUser);
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }



    @RequestMapping(value = "groupname_query.json", method = RequestMethod.GET)
    @ResponseBody
    public boolean queryGroupname(/*@Validated(value = {Query.class}) SysGroup sysGroup, BindingResult bindingResult,
                                                     Pageable pageable, */
                                                     String groupname,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        return sysGroupService.getByGroupname(groupname) == null;
    }
}
