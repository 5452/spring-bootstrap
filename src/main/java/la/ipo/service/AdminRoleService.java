package la.ipo.service;

import java.util.List;

import la.ipo.admin.model.Role;
import la.ipo.service.base.AdminBaseService;

public interface AdminRoleService extends AdminBaseService {

	public void add(Role role);

	public void delete(Long id) throws Exception;

	public void update(Role role);

	public Role getById(Long id);

	public List<Role> findAll();
	
}
