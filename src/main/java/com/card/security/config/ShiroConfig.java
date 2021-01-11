package com.card.security.config;

import com.card.security.JWTRealm;
import com.card.security.NoSessionFilter;
import com.card.security.StatelessDefaultSubjectFactory;
import com.card.security.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     *
     * @return DefaultAdvisorAutoProxyCreator的顺序必须在shiroFilterFactoryBean之前，不然SecurityUtils.getSubject().getPrincipal()获取不到参数
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        advisorAutoProxyCreator.setUsePrefix(true);
        return advisorAutoProxyCreator;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager());

        // 过滤规则
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        /*添加shiro的内置过滤器
         * anon 无需认证就能访问
         * authc  必须认证了才能访问
         * user  必须拥有记住我才能访问
         * perms  拥有对某个资源的权限才能访问
         * roles  拥有某个角色权限才能访问
         * 参考：https://www.cnblogs.com/koal/p/5152671.html
         * */
        // 用户权限
        linkedHashMap.put("/api/user/selectPage", "perms[user:select]");
        linkedHashMap.put("/api/user/selectById", "perms[user:select]");
        linkedHashMap.put("/api/user/updateById", "perms[user:update]");
        linkedHashMap.put("/api/user/removeByIds", "perms[user:delete]");
        // 商品权限
        linkedHashMap.put("/api/product/deleteBatchIds", "perms[product:delete]");
        linkedHashMap.put("/api/product/updateById", "perms[product:update]");
        linkedHashMap.put("/api/product/insert", "perms[product:add]");

        // 角色权限
        linkedHashMap.put("/api/role/saveOrUpdate", "perms[role:add,role:update]");
        linkedHashMap.put("/api/role/removeByIds", "perms[role:delete]");
        linkedHashMap.put("/api/role/getById", "perms[role:select]");
        linkedHashMap.put("/api/role/selectPage", "perms[role:select]");

        // 菜单权限
        linkedHashMap.put("/api/menuList/removeByIds", "perms[menuList:delete]");
        linkedHashMap.put("/api/menuList/saveOrUpdate", "perms[menuList:add,menuList:update]");

        // 订单权限
        linkedHashMap.put("/api/order/deleteBatchIds", "perms[order:delete]");
        linkedHashMap.put("/api/order/updateById", "perms[order:update]");

        // 授权的权限
        linkedHashMap.put("/api/rolePermission/saveOrUpdate", "perms[rolePermission:add]");
        linkedHashMap.put("/api/rolePermission/removeByIds", "perms[rolePermission:delete]");

        // 自定义过滤器
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        filterHashMap.put("jwt", new NoSessionFilter());
        shiroFilterFactoryBean.setFilters(filterHashMap);
        // 登录之后才可以请求的接口
        linkedHashMap.put("/api/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealms(Arrays.asList(userRealm(), jwtRealm()));
        // 禁用shiro中的session
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(defaultSubjectDAO);
        defaultWebSecurityManager.setSubjectFactory(subjectFactory());
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public JWTRealm jwtRealm() {
        return new JWTRealm();
    }

    @Bean
    public StatelessDefaultSubjectFactory subjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    /*
     * 凭证匹配器 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于MD5(MD5(""));
        return hashedCredentialsMatcher;
    }
}