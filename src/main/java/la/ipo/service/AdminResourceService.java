package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.Resource;
import la.ipo.service.base.AdminBaseService;

public interface AdminResourceService extends AdminBaseService{

	public List<Resource> query(Resource resource);

	public void add(Resource resource);

	public void delete(Long id);

	public void updateResource(Resource resource);

	public Resource getById(Long id);

	public List<Resource> findAll();

	public List<Resource> getUserResourcesByUserId(Long userId);

	public List<Resource> getRoleResourcesByRoleId(Long roleId);

	public List<Resource> getResourcesByEmail(String email);

	public void saveRoleResources(Long roleId, List<Long> list);
}
