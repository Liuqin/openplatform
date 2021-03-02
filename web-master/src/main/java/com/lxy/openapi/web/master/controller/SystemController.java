package com.lxy.openapi.web.master.controller;

import com.lxy.openapi.web.master.pojo.AdminUser;
import com.lxy.openapi.web.master.pojo.Menu;
import com.lxy.openapi.web.master.service.AdminUserService;
import com.lxy.openapi.web.master.service.MenuService;
import com.lxy.openapi.web.master.util.AdminConstants;
import com.lxy.openapi.web.master.bean.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 将session设置为失效
        session.invalidate();
        return "login.html";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public AjaxMessage login(String email, String password, HttpSession session) {
        AdminUser user = adminUserService.doLogin(email, password);
        if (user == null) {
            return new AjaxMessage(false);
        }
        session.setAttribute(AdminConstants.SESSION_USER, user);
        List<Menu> menuList = menuService.getUserMenuList(user.getId());
        session.setAttribute(AdminConstants.USER_MENU,menuList);
        return new AjaxMessage(true);
    }

    @RequestMapping("/auth_error")
    public String error() {
        return "error.html";
    }

    @RequestMapping("/side")
    @ResponseBody
    public AjaxMessage getMenuTree(HttpSession session, HttpServletResponse response) {
        AdminUser user = (AdminUser) session.getAttribute(AdminConstants.SESSION_USER);
        if (user == null) {
            try {
                response.sendRedirect("/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new AjaxMessage(true, null, new ArrayList<>());
        }
        List<Menu> menus = menuService.getUserPermission(user.getId());
        return new AjaxMessage(true, null, menus);
    }
}
