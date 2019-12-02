package com.hwy.shipyard.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 实现权限或功能
 */
public class CustomPermissionOrAuthorizationFilter extends PermissionsAuthorizationFilter {
/*    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
        String[] permsArray = (String[]) mappedValue;

        //没有权限限制，可以直接访问
        if (permsArray == null || permsArray.length == 0) {
            //no perms specified, so nothing to check - allow access.
            return true;
        }


        Set<String> perms = CollectionUtils.asSet(permsArray);

        //当前subject是perm 中的任意一个，则有权限访问
        for (String perm : perms) {
            if (subject.hasRole(perm)) {
                return true;
            }
        }
        return false;
    }*/
}

