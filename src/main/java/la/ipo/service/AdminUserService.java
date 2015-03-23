package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.User;
import la.ipo.admin.model.UserRole;
import la.ipo.service.base.AdminBaseService;

import org.springframework.security.core.AuthenticationException;


public interface AdminUserService extends AdminBaseService {
	
	/**
	 * 通过ID获取用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	
	/**
	 * 对象式查询符合条件的用户集合
	 * @param user
	 * @return
	 */
	public List<User> getUserByParam(User user);
	
	/**
	 * 根据给定的email获取用户信息
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
	/**
	 * 添加用户
	 * @param user
	 * @param roleId
	 */
	public void addUser(User user, Long roleId);
	
	/**
	 * 更新用户
	 * @param user
	 */
	public void updateUser(User user, Long roleId);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(Long id);
	
	/**
	 * 验证用户登录
	 * @param email
	 * @param password
	 * @return
	 */
	public User authUser(String email, String password) throws AuthenticationException;
	
	/**
	 * 查询出所用用户
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 更新用户角色，只支持用户绑定单一角色
	 * @param userRole
	 */
	public void updateUserRole(UserRole userRole);
}
