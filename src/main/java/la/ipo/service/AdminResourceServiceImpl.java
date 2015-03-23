/**
 * 
 */
package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.Resource;
import la.ipo.admin.model.RoleResource;
import la.ipo.admin.mybatis.ResourceMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 2014年3月26日 下午2:10:58
 * @author WANGJUN
 *
 */
@Service("adminResourceService")
public class AdminResourceServiceImpl implements AdminResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<Resource> query(Resource resource) {
		// TODO
		return null;
	}

	@Override
	public void add(Resource resource) {
		resourceMapper.add(resource);
	}

	@Override
	public void delete(Long id) {
		resourceMapper.deleteById(id);
	}

	@Override
	public void updateResource(Resource resource) {
		resourceMapper.update(resource);
	}

	@Override
	public Resource getById(Long id) {
		return resourceMapper.getById(id);
	}

	@Override
	public List<Resource> findAll() {
		return resourceMapper.findAllByPage();
	}

	@Override
	public List<Resource> getUserResourcesByUserId(Long userId) {
		return resourceMapper.getUserResourcesByUserId(userId);
	}

	@Override
	public List<Resource> getRoleResourcesByRoleId(Long roleId) {
		return resourceMapper.getRoleResourcesByRoleId(roleId);
	}

	@Override
	public List<Resource> getResourcesByEmail(String email) {
		return resourceMapper.getUserResourcesByEmail(email);
	}

	@Override
	@Transactional
	public void saveRoleResources(Long roleId, List<Long> list) {
		resourceMapper.deleteRoleResourceByRoleId(roleId);
		for (Long resourceId : list) {
			if(resourceId != null && resourceId > 0){
				RoleResource roleResource = new RoleResource(roleId, resourceId);
				resourceMapper.addRoleRescourses(roleResource);
			}
		}
	}
}
