package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.User;
import la.ipo.admin.model.UserRole;
import la.ipo.admin.mybatis.RoleMapper;
import la.ipo.admin.mybatis.UserMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public User getUserById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public List<User> getUserByParam(User user) {
		return userMapper.getByParams(user);
	}

	@Override
	@Transactional
	public void addUser(User user, Long roleId) throws RuntimeException {
		if(user != null) {
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userMapper.add(user);
		}
		if(roleId != null && roleId > 0) {
			User userInDatabase = userMapper.getByEmail(user.getEmail());
			UserRole userRole = new UserRole(roleId, userInDatabase.getId());
			roleMapper.addUserRole(userRole);
		}
	}
	
	@Override
	@Transactional
	public void updateUser(User user, Long roleId) {
		if(user != null && user.getId() != null && user.getId() > 0) {
			if(StringUtils.isNotBlank(user.getPassword()))
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userMapper.update(user);
			if(roleId != null && roleId > 0) {
				roleMapper.deleteUserRoleByUserId(user.getId());
				UserRole userRole = new UserRole(roleId, user.getId());
				roleMapper.addUserRole(userRole);
			}
		}
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		if(id != null && id > 0) {
			userMapper.delete(id);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		return userMapper.getByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAllByPage();
	}

	@Override
	@Transactional
	public void updateUserRole(UserRole userRole) {
		if(userRole != null && userRole.getRoleId() != null && userRole.getRoleId() > 0 && userRole.getUserId() != null && userRole.getUserId() > 0) {
			roleMapper.deleteUserRoleByUserId(userRole.getUserId());
			roleMapper.addUserRole(userRole);
		}
	}
	
	@Override
	public User authUser(String email, String password) throws AuthenticationException {
		String msg = "登陆失败，Email或密码错误, email:" + email + ", password:" + password;
		User user = userMapper.getByEmail(email);
		try {
			if (user.getId() > 0 && bcryptEncoder.matches(password, user.getPassword()))
				return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationServiceException(msg);
		}
		throw new AuthenticationServiceException(msg);
	}

}
