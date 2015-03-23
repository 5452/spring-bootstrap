/**
 * 
 */
package la.ipo.web;

import java.io.IOException;
import java.util.List;

import la.ipo.admin.model.Resource;
import la.ipo.admin.model.Role;
import la.ipo.pagination.PageContext;
import la.ipo.service.AdminResourceService;
import la.ipo.service.AdminRoleService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 2014年2月12日 下午4:34:44
 * @author WANGJUN
 *
 */
@Controller
@RequestMapping(value="/admin_role")
public class AdminRoleController {

	@Autowired
	private AdminResourceService adminResourceService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("role", new Role());
		return "admin_role/edit";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String processAdd(Role role, Long roleId) {
		adminRoleService.add(role);
		return "redirect:/admin_role/list";
	}
	
	/**
	 * 删除用户
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete")
	public String delete(Long id) throws Exception {
		adminRoleService.delete(id);
		return "redirect:/admin_role/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		model.addAttribute("role", adminRoleService.getById(id));
		return "admin_role/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(Role role) {
		adminRoleService.update(role);
		return "redirect:/admin_role/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model, Integer page) {
		PageContext pageContext = PageContext.getContext();
		pageContext.setCurrentPage(page == null ? 1 : page);
		pageContext.setPagination(true);
		model.addAttribute("roleList", adminRoleService.findAll());
		model.addAttribute("pageContext", pageContext);
		return "admin_role/list";
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public String assignPermissions(Model model, Long id) throws JsonGenerationException, JsonMappingException, IOException {
		List<Resource> roleResources = adminResourceService.getRoleResourcesByRoleId(id);
		PageContext pageContext = PageContext.getContext();
		pageContext.setPagination(false);
		List<Resource> allResources = adminResourceService.findAll();
		for(Resource resource : allResources) {
			if(roleResources.contains(resource))
				resource.setPermitted(true);
		}
		model.addAttribute("roleId", id);
		model.addAttribute("resources", objectMapper.writeValueAsString(allResources));
		return "admin_role/assign";
	}
	
	@RequestMapping(value="/assign", method = RequestMethod.POST)
	public ResponseEntity<String> permission(Long roleId, @RequestParam("resources") List<Long> resources) {
		adminResourceService.saveRoleResources(roleId, resources);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
