package com.hwy.shipyard.config;



import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //需要登录的接口，未登录访问需登录的接口，调用该接口（前后端不分离则跳转页面
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");

        //前后分离无此设置
        //shiroFilterFactoryBean.setSuccessUrl("/");

        //未授权调用此方法，先验证登录在验证权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permission");


        //设置自定义Filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter",new CustomRolesOrAuthorizationFilter());
        //filterMap.put("permOrFilter",new CustomPermissionOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截路径！
        //注意：部分路径无法进行拦截，时有时无，可能使用的是HASHMAP无序，应用linked有序
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();


        filterChainDefinitionMap.put("/redis/**","anon");

        filterChainDefinitionMap.put("/item/**","anon");

        //退出过滤器
        //filterChainDefinitionMap.put("/logout","logout");

        //匿名访问,游客模式
        filterChainDefinitionMap.put("/pub/**","anon");
        filterChainDefinitionMap.put("/logout","anon");

        //登录可访问
        filterChainDefinitionMap.put("/authc/**","authc");

        //仓储部门
        filterChainDefinitionMap.put("/warehouse/add","roleOrFilter[root,warehouse_root]");
        filterChainDefinitionMap.put("/warehouse/del","roleOrFilter[root,warehouse_root]");
        filterChainDefinitionMap.put("/warehouse/allocation/state/**","roleOrFilter[root,warehouse_root]");
        filterChainDefinitionMap.put("/warehouse/**","roleOrFilter[root,warehouse_admin,warehouse_root]");

        //采购部门
        filterChainDefinitionMap.put("/purchase/apply/check","roleOrFilter[root,purchase_root]");
        filterChainDefinitionMap.put("/purchase/state","roleOrFilter[root,purchase_root]");
        filterChainDefinitionMap.put("/purchase/**","roleOrFilter[root,purchase_admin,purchase_root]");
        filterChainDefinitionMap.put("/product/add","roleOrFilter[root,purchase_admin,purchase_root]");

        //备件
        filterChainDefinitionMap.put("/application/update/**","roleOrFilter[root,application_root]");
        filterChainDefinitionMap.put("/application/**","roleOrFilter[root,application_admin,application_root]");

        //维保
        filterChainDefinitionMap.put("/schedule/update/**","roleOrFilter[root,schedule_root]");
        filterChainDefinitionMap.put("/schedule/**","roleOrFilter[root,schedule_admin,schedule_root]");
        filterChainDefinitionMap.put("/maintenance/update/**","roleOrFilter[root,schedule_root]");
        filterChainDefinitionMap.put("/maintenance/all","roleOrFilter[root,schedule_root,schedule_admin]");
        filterChainDefinitionMap.put("/request/**","roleOrFilter[root,schedule_admin,schedule_root]");

        //后台管理系统仅root用户可访问

        filterChainDefinitionMap.put("/sys/user/update/info","authc");
        filterChainDefinitionMap.put("/sys/user/update/pwd","authc");
        filterChainDefinitionMap.put("/sys/**","roleOrFilter[root]");
        filterChainDefinitionMap.put("/check/**","roleOrFilter[root]");

        //有更新权限才可以访问
        //filterChainDefinitionMap.put("/video/update","perms[pcorder_update]");


        //注意 2.过滤链是顺序执行，从上而下，一般/**放到最下面
        //authc: url定义必须通过认证才可以访问
        //anno: url可以匿名访问
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();


        //使用自定义Catch
        //securityManager.setCacheManager(cacheManager());

        securityManager.setSessionManager(sessionManager());
        //不能new
        securityManager.setRealm(customRealm());
        return securityManager;
    }


    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        //加密器
        //customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    //自定义SessionManager
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();

        //默认超时时间30分钟，session过期时间，单位ms
        customSessionManager.setGlobalSessionTimeout(1000*60*60);

        //配置Session持久化
        //customSessionManager.setSessionDAO(redisSessionDAO());

        return customSessionManager;
    }

    /**
     * 配置redisManager
     */
/*    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();

        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }*/

 /*   *//**
     * 配置具体Cache实现类
     *//*
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());

        //设置过期时间，单位秒
        redisCacheManager.setExpire(60*5);
        return redisCacheManager;
    }

    *//**
     *
     * 自定义Session持久化
     *//*
    public RedisSessionDAO redisSessionDAO(){
       RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
       redisSessionDAO.setRedisManager(getRedisManager());
       return redisSessionDAO;

    }*/



}
