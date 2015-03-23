/**
 * 
 */
package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.Role;
import la.ipo.admin.model.UserRole;
import la.ipo.admin.mybatis.RoleMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2014年3月27日 下午1:47:36
 * @author WANGJUN
 *
 */
@Service("roleService")
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public void add(Role role) {
		if(role != null && StringUtils.isNotBlank(role.getName()) && StringUtils.isNotBlank(role.getKey()))
			roleMapper.add(role);
	}

	@Override
	public void delete(Long id) throws Exception {
		List<UserRole> userRoles = roleMapper.findUserRolesByRoleId(id);
		if(userRoles == null || userRoles.size() == 0) {
			roleMapper.deleteRoleById(id);
		} else 
			throw new Exception("角色下存在用户，不能删除");
	}

	@Override
	public void update(Role role) {
		if(role != null && role.getId() != null && role.getId() > 0)
			roleMapper.updateRoleById(role);
	}

	@Override
	public Role getById(Long id) {
		return roleMapper.getRoleById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleMapper.findAllByPage();
	}

}
