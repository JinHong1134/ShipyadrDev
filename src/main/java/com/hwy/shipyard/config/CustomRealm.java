package com.hwy.shipyard.config;




import com.hwy.shipyard.dataobject.Authorization;
import com.hwy.shipyard.dataobject.Role;
import com.hwy.shipyard.dataobject.User;
import com.hwy.shipyard.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");

        User user1 = (User)principalCollection.getPrimaryPrincipal();
        User user = userService.getUserById(user1.getUserId());

        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();


        List<Role> roleList = user.getRoleList();

        System.out.println(roleList);
        for (Role role : roleList){
            stringRoleList.add(role.getRoleName());

            List<Authorization> permissionList = role.getPermissionList();

            for(Authorization permission : permissionList){
                if(permission!=null){
                    stringPermissionList.add(permission.getAuthorizationName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
/*        System.out.println("授权 doGetAuthorizationInfo");
        User user1 = (User)principals.getPrimaryPrincipal();
        User user = userService.getUserById(user1.getUserId());


        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();


        List<Role> roleList = user.getRoleList();

        for(Role role : roleList){
            stringRoleList.add(role.getRoleName());

            List<Permission> permissionList = role.getPermissionList();

            for(Permission p: permissionList){
                if(p!=null){
                    stringPermissionList.add(p.getName());
                }
            }

        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;*/

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");

        String id = (String)authenticationToken.getPrincipal();

        User user = userService.getUserById(id);

        String password = user.getUserPassword();
        if (password==null || "".equals(password)){
            return null;
        }


        return new SimpleAuthenticationInfo(user,user.getUserPassword(), this.getClass().getName());
    }
}
