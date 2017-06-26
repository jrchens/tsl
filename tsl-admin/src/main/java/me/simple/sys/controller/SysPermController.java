package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysPerm;
import me.simple.sys.service.SysPermService;
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
@RequestMapping("sys_perm")
public class SysPermController {
    private static final Logger logger = LoggerFactory.getLogger(SysPermController.class);
    private String modelname = "model.sys_perm";

    @Autowired
    private SysPermService sysPermService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        model.addAttribute(sysPerm);
        return "sys_perm/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        model.addAttribute(sysPerm);
        return "sys_perm/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_perm/create";
//        }
//        sysPermService.save(sysPerm, currentUser);
//        return "redirect:/sys_perm/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        boolean success = true;
        String message = "save.success";

        Map<String,Object> body = Maps.newHashMap();

        Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
            success = false;
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//                data.put(fe.getField(),messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            message = "field.bind.error";
        }else{
            sysPermService.save(sysPerm, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_perm/index";
//        }
//        sysPermService.remove(sysPerm, currentUser);
//        return "redirect:/sys_perm/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(/*@Validated(value = {Remove.class}) */SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = "remove.success";;
        Map<String,Object> body = Maps.newHashMap();

//        Map<String,Object> data = Maps.newHashMap();

        if (bindingResult.hasErrors()) {
            success = false;
            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//                data.put(fe.getField(),
//                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            message = "field.bind.error";
        }else{
            sysPermService.remove(sysPerm, currentUser);
        }

        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_perm/index";
        }
        model.addAttribute(sysPermService.get(sysPerm, currentUser));
        return "sys_perm/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_perm/edit";
//        }
//        sysPermService.update(sysPerm, currentUser);
//        return "redirect:/sys_perm/index";
//    }


    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
        boolean success = true;
        String message = "update.success";
        Map<String,Object> body = Maps.newHashMap();

        // Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//               data.put(fe.getField(),
//                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            success = false;
            message = "field.bind.error";
        }else{
             sysPermService.update( sysPerm, currentUser);
        }
    
        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));
    
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysPerm sysPerm, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysPerm> rows = sysPermService.query(sysPerm, pageable, currentUser);
        // List<SysMenu> rows = sysMenuService.query(sysMenu, pageable, currentUser);
        if(pageable.getPage() == 1) {
            SysPerm self = sysPermService.get(new SysPerm(sysPerm.getId()),currentUser);
            rows.add(0,self);
            pageable.setTotal(pageable.getTotal() + 1);
        }
        int total = pageable.getTotal();

        Map<String, Object> body = Maps.newHashMap();
        body.put("rows", rows);
        body.put("total", total);

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "tree.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> tree(@Validated(value = {Query.class}) SysPerm sysPerm, BindingResult bindingResult,
                                                    Pageable pageable,
                                                    @LoginedUser CurrentUser currentUser, Model model) {

        String message = "query.tree.success";

        Map<String, Object> body = Maps.newHashMap();
        body.put("success",true);
        body.put("data",sysPermService.getTree());
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "children.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> children(@Validated(value = {Query.class}) SysPerm sysPerm, BindingResult bindingResult,
                                                        Pageable pageable,
                                                        @LoginedUser CurrentUser currentUser, Model model) {

        String message = "query.tree.children.success";

        Map<String, Object> body = Maps.newHashMap();
        body.put("success",true);
        body.put("data",sysPermService.getChildrenByPid(sysPerm.getId()));
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }




    @RequestMapping(value = "get.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> get(@Validated(value = {Get.class}) SysPerm sysPerm, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        boolean success = true;
        String message = "get.success";
        Map<String,Object> body = Maps.newHashMap();

        // Map<String,Object> data = Maps.newHashMap();
        if (bindingResult.hasErrors()) {
//            List<FieldError> feList = bindingResult.getFieldErrors();
//            for (FieldError fe: feList) {
//               data.put(fe.getField(),
//                messageSource.getMessage(fe.getCode(),fe.getArguments(),fe.getDefaultMessage(),null));
//            }
            success = false;
            message = "field.bind.error";
        }else{
            body.put("data",sysPermService.get( sysPerm, currentUser));
        }

        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }
}
