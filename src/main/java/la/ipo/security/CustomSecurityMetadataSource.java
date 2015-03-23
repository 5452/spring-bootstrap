package la.ipo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import la.ipo.admin.model.Resource;
import la.ipo.admin.mybatis.ResourceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * 加载资源与权限的对应关系
 * 
 * */
@Service("securityMetadataSource")
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private ResourceMapper resourceMapper;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * @PostConstruct是Java EE 5引入的注解
	 * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 *  加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = this.resourceMapper.findAll();
			for (Resource resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig(resource.getKey());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getUrl(), configAttributes);
			}
		}
	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null) {
			loadResourceDefine();
		}
		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		/**
		 * 以下判断过程的作用是：凡是没说允许的，就是不允许的
		 * 如果删除掉判断和操作过程，意思变成：凡是没说不允许的，就是允许的
		 */
		if(configAttributes == null) {
			configAttributes = new ArrayList<ConfigAttribute>();
			configAttributes.add(new SecurityConfig("not_permit"));
		}
		return configAttributes;
	}

}