package com.tor.sys.controller;

import com.tor.common.base.BaseController;
import com.tor.project.entity.User;
import com.tor.project.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@Controller
public class AdminController extends BaseController {
    private Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping({ "/", "" })
    String welcome() {
        return "redirect:/login";
    }

    @GetMapping({ "/index" })
    String index(Model model) {
        log.info("============================= 欢迎进入index =============================");
        return "index";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        log.info("============================= 欢迎登录login =============================");
        return "login";
    }

    @PostMapping(value="/login")
    public String login(HttpServletRequest request, User user){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            return "redirect:index";
        }catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(){
        log.info("============================= 退出logout =============================");
        return "login";
    }
    @RequestMapping(value={"/usersPage",""})
    public String usersPage(){
        return "user/users";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage(){
        return "role/roles";
    }

    @RequestMapping("/resourcesPage")
    public String resourcesPage(){
        return "resources/resources";
    }

    @RequestMapping("/403")
    public String forbidden(){
        return "403";
    }
}
