package com.lxy.openapi.web.master.service;


import com.lxy.openapi.web.master.pojo.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuTree();

    List<Menu> getMenuList();

    List<Menu> getFullMenuTree();

    void deleteMenus(Integer[] ids);

    void addMenu(Menu menu);

    Menu getMenuById(Integer id);

    void updateMenu(Menu menu);

    List<Menu> getUserPermission(Integer userId);

    List<Menu> getUserMenuList(Integer userId);

}
