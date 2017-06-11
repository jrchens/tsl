package me.simple.sys.controller;

import com.google.common.collect.Maps;
import me.simple.domain.CurrentUser;
import me.simple.domain.SysGroup;
import me.simple.domain.SysRole;
import me.simple.domain.SysUser;
import me.simple.sys.service.SysGroupService;
import me.simple.sys.service.SysRoleService;
import me.simple.sys.service.SysUserService;
import me.simple.web.method.support.LoginedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by chensheng on 17/5/25.
 */

@Controller
public class ValidatorController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysGroupService sysGroupService;
    @Autowired
    private SysRoleService sysRoleService;


    /**
     * @param code  <pre>
     *                  100: sys_user.username unique
     *                  110: sys_group.groupname unique
     *                  120: sys_role.rolename unique
     *              </pre>
     * @param param
     * @return
     */
    @RequestMapping(value = "valid.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> valid(
            @RequestParam(required = false, defaultValue = "0") int code,
            @RequestParam(required = false) Map<String, String> param
    ) {
        boolean success = true;
        String message = null;


        Map<String, Object> body = Maps.newHashMap();
        Map<String, Object> data = Maps.newHashMap();

        if (code == 100) {
            String username = param.get("username");
            SysUser obj = sysUserService.getByUsername(username);
            if (obj != null) {
                success = false;
                message = messageSource.getMessage("value.exists", new Object[]{username}, "value.exists", null);
            }
        } else if (code == 110) {
            String groupname = param.get("groupname");
            SysGroup obj = sysGroupService.getByGroupname(groupname);
            if (obj != null) {
                success = false;
                message = messageSource.getMessage("value.exists", new Object[]{groupname}, "value.exists", null);
            }
        } else if (code == 120) {
            String rolename = param.get("rolename");
            SysRole obj = sysRoleService.getByRolename(rolename);
            if (obj != null) {
                success = false;
                message = messageSource.getMessage("value.exists", new Object[]{rolename}, "value.exists", null);
            }
        }

        body.put("success", success);
        body.put("message", message);

        return ResponseEntity.ok(body);
    }
}
