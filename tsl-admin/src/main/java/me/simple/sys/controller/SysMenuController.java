package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.Pageable;
import me.simple.domain.SysMenu;
import me.simple.sys.service.SysMenuService;
import me.simple.validator.group.*;
import me.simple.web.method.support.LoginedUser;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
@RequestMapping("sys_menu")
public class SysMenuController {
    private static final Logger logger = LoggerFactory.getLogger(SysMenuController.class);
    private String modelname = "model.sys_menu";

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@Validated(value = {Index.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        sysMenu.setSrt(10);
        model.addAttribute(sysMenu);
        return "sys_menu/index";
    }

    public void init(final Model model){
        model.addAttribute(sysMenuService.querySysMenuGroup());
        model.addAttribute(sysMenuService.getRootNode());
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@Validated(value = {Create.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
        init(model);
        model.addAttribute(sysMenu);
        return "sys_menu/create";
    }

//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(@Validated(value = {Save.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_menu/create";
//        }
//        sysMenuService.save(sysMenu, currentUser);
//        return "redirect:/sys_menu/index";
//    }


    @RequestMapping(value = "save.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> asave(@Validated(value = {Save.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
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
            sysMenuService.save(sysMenu, currentUser);
        }

        body.put("success",success);
        body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String remove(@Validated(value = {Remove.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_menu/index";
//        }
//        sysMenuService.remove(sysMenu, currentUser);
//        return "redirect:/sys_menu/index";
//    }


    @RequestMapping(value = "remove.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aremove(/*@Validated(value = {Remove.class}) */SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
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
            sysMenuService.remove(sysMenu, currentUser);
        }

        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@Validated(value = {Edit.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

        if (bindingResult.hasErrors()) {
            return "sys_menu/index";
        }
        init(model);
        model.addAttribute(sysMenuService.get(sysMenu, currentUser));
        return "sys_menu/edit";
    }

//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    public String update(@Validated(value = {Update.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "sys_menu/edit";
//        }
//        sysMenuService.update(sysMenu, currentUser);
//        return "redirect:/sys_menu/index";
//    }


    @RequestMapping(value = "update.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> aupdate(@Validated(value = {Update.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {
    
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
             sysMenuService.update( sysMenu, currentUser);
        }
    
        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));
    
        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(@Validated(value = {Query.class}) SysMenu sysMenu, BindingResult bindingResult,
                                                     Pageable pageable,
                                                     @LoginedUser CurrentUser currentUser, Model model) {
        List<SysMenu> rows = sysMenuService.query(sysMenu, pageable, currentUser);
        if(pageable.getPage() == 1) {
            SysMenu self = sysMenuService.get(new SysMenu(sysMenu.getId()),currentUser);
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
    public ResponseEntity<Map<String, Object>> tree(@Validated(value = {Query.class}) SysMenu sysMenu, BindingResult bindingResult,
                                                    Pageable pageable,
                                                    @LoginedUser CurrentUser currentUser, Model model) {

        String message = "query.tree.success";

        Map<String, Object> body = Maps.newHashMap();
        body.put("success",true);
        body.put("data",sysMenuService.querySysMenuGroup());
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }

    @RequestMapping(value = "children.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> children(@Validated(value = {Query.class}) SysMenu sysMenu, BindingResult bindingResult,
                                                    Pageable pageable,
                                                    @LoginedUser CurrentUser currentUser, Model model) {

        String message = "query.tree.children.success";

        Map<String, Object> body = Maps.newHashMap();
        body.put("success",true);
        body.put("data",sysMenuService.getChildrenByPid(sysMenu.getId()));
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }




    @RequestMapping(value = "get.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> get(@Validated(value = {Get.class}) SysMenu sysMenu, BindingResult bindingResult, @LoginedUser CurrentUser currentUser, Model model) {

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
            body.put("data",sysMenuService.get( sysMenu, currentUser));
        }

        body.put("success",success);
        // body.put("data",data);
        body.put("message",messageSource.getMessage(message,new Object[]{modelname},null));

        return ResponseEntity.ok(body);
    }
}
