package com.tor.sys.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
@Controller
@AllArgsConstructor
public class AdminController {

    @GetMapping({ "/", "" })
    String welcome(Model model) {
        return "redirect:/login";
    }

    @GetMapping({ "include"})
    String inde(Model model) {
        return "include";
    }

    @GetMapping({ "/index" })
    String index(Model model) {
//        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
//        model.addAttribute("menus", menus);
//        model.addAttribute("name", getUser().getName());
//        model.addAttribute("username", getUser().getUsername());
//        FileDO fileDO = fileService.selectById(getUser().getPicId());
//        model.addAttribute("picUrl", fileDO == null ? "/img/photo_s.jpg" : fileDO.getUrl());
//        List<MpConfigDO> mpList = mpConfigService.selectList(null);
//        model.addAttribute("mpList", mpList);
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

//    @Log("登录")
//    @PostMapping("/login")
//    @ResponseBody
//    Result<String> ajaxLogin(String username, String password) {
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
////        token.setRememberMe(true);//记住我是可选项，但只有会话缓存到redis等持久存储才能真正记住
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//            return Result.ok();
//        } catch (AuthenticationException e) {
//            return Result.build(EnumErrorCode.userLoginFail.getCode(), EnumErrorCode.userLoginFail.getMsg());
//        }
//    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/403")
    String error403() {
        return "403";
    }

}
