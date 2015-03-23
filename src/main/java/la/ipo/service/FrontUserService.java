package la.ipo.service;

import java.util.List;

import la.ipo.model.User;
import la.ipo.model.UserExample;
import la.ipo.service.base.FrontBaseService;

public interface FrontUserService extends FrontBaseService {

	/**
	 * 通过ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);

	/**
	 * 对象式查询符合条件的用户集合
	 * 
	 * @param user
	 * @return
	 */
	public List<User> getUserByParam(UserExample example);

	/**
	 * 根据给定的email获取用户信息
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param roleId
	 */
	public void addUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void deleteUser(Long id);

	/**
	 * 查询出所用用户
	 * 
	 * @return
	 */
	public List<User> findAll();
}
