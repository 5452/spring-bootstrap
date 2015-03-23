package la.ipo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import la.ipo.admin.model.Resource;
import la.ipo.admin.model.User;
import la.ipo.admin.mybatis.ResourceMapper;
import la.ipo.admin.mybatis.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom Spring Security  权限验证类
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 *
 */
/**
 * Custom Spring Security 权限验证类 获得对象的方式： WebUserDetails webUserDetails =
 * (WebUserDetails
 * )SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 或在JSP中： <sec:authentication property="principal.username"/> 2014年3月21日
 * 
 * @author WANGJUN
 * 
 */
@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceMapper resourceMapper;

	// 登录验证
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userMapper.getByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
		if (null == user)
			throw new UsernameNotFoundException(email + " not exist!");
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		// 封装成spring security的user
		org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true, true, true,
				grantedAuths // 用户的权限
		);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		List<Resource> resources = resourceMapper.getUserResourcesByUserId(user.getId());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resource resource : resources) {
			authSet.add(new SimpleGrantedAuthority(resource.getKey()));
		}
		return authSet;
	}
}