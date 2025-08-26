package com.carbon.common.constants;

/**
 * 常量
 *
 * @author Li Jun
 * @since 2021-06-11
 */
public interface CommonConstant {

    /**
     * 多租户 请求头
     */
    String TENANT_ID = "tenant_id";

    /**
     * 登陆token
     */
    String TOKEN = "token";

    /**
     * 刷新token
     */
    String REFRESH_TOKEN = "refresh_token";


    /**
     * COOKIE 标识
     */
    String UUID_COOKIE = "uuidCookie";

    /**
     * 菜单类型-功能模块
     */
    Integer MENU_TYPE_MODULE = -1;
    /**
     * 菜单类型-目录
     */
    Integer MENU_TYPE_0 = 0;
    /**
     * 菜单类型-菜单
     */
    Integer MENU_TYPE_1 = 1;
    /**
     * 菜单类型-按钮
     */
    Integer MENU_TYPE_2 = 2;


    /**
     * websocket请求路径
     */
    String WEBSOCKET = "/websocket";

}
