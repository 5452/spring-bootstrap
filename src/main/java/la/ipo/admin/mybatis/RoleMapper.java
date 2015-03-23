/**
 * 
 */
package la.ipo.admin.mybatis;

import java.util.List;

import la.ipo.admin.model.Role;
import la.ipo.admin.model.UserRole;

/**
 * 
 * 2014年3月24日 下午3:16:20
 * @author WANGJUN
 *
 */
public interface RoleMapper {
	
	public void add(Role role);
	
	public void deleteRoleById(Long id);
	
	public void updateRoleById(Role role);
	
	public Role getRoleById(Long id);

	public List<Role> findAllByPage();
	
	public List<Role> findRolesByUserId(Long userId);
	
	public List<UserRole> findUserRolesByRoleId(Long roleId);
	
	public Integer countByLikeName(String name);
	
	public List<Role> getByLikeName(String name);
	
	public void addUserRole(UserRole userRole);
	
	public void deleteUserRoleByUserId(Long userId);
	
	public void updateUserRoleByUserId(UserRole userRole);
}
