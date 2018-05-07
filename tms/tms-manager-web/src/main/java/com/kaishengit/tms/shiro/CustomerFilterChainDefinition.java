package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/*非
 *
 * @author
 * @date 2018/4/18
 */
public class CustomerFilterChainDefinition {

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);
    @Autowired
    private RolePermissionService rolePermissionService;

    private String filterChainDefinitions;

    private AbstractShiroFilter shiroFilter;

    public void setFilterChainDefinitions(String filterChainDefinitions){
        this.filterChainDefinitions = filterChainDefinitions;
    }
    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }
    /**
     * Spring
     */
    @PostConstruct
    public synchronized void init() {
        logger.info("-----------------");
        //
        getFilterChainManager().getFilterChains().clear();
        //
        load();
        logger.info("-----------------");
    }

    /**
     *
     */
    public synchronized void updateUrlPermission() {
        logger.info("------ˢ-----------");
        //
        getFilterChainManager().getFilterChains().clear();
        //
        load();
        logger.info("------ˢ-----------");
    }

    /**
     *
     */
    public synchronized void load() {
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);

        //
        List<Permission> permissionList = rolePermissionService.findAllPermission();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for(Permission permission : permissionList) {
            section.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }
        section.put("/**","user");

        //
        DefaultFilterChainManager defaultFilterChainManager = getFilterChainManager();
        for(Map.Entry<String,String> entry : section.entrySet()) {
            defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }
    }


    private DefaultFilterChainManager getFilterChainManager() {
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
        return defaultFilterChainManager;
    }

}
