/**
 * 
 */
package la.ipo.admin.mybatis;

import java.util.List;

import la.ipo.admin.model.Resource;
import la.ipo.admin.model.RoleResource;

/**
 * 
 * 2014年3月24日 下午3:16:20
 * @author WANGJUN
 *
 */
public interface ResourceMapper {
	
	public void add(Resource resource);
	
	public void deleteById(Long id);
	
	public void update(Resource resource);
	
	public Resource getById(Long id);

	public List<Resource> findAll();
	
	public List<Resource> findAllByPage();
	
	public List<Resource> getUserResourcesByUserId(Long userId);
	
	public List<Resource> getUserResourcesByEmail(String email);
	
	public List<Resource> getRoleResourcesByRoleId(Long roleId);
	
	public Integer countByLikeName(String name);
	
	public List<Resource> getByLikeName(String name);
	
	public void addRoleRescourses(RoleResource roleResource);
	
	public void deleteRoleResourceByRoleId(Long roleId);
}
