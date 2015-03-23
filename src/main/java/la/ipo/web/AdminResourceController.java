/**
 * 
 */
package la.ipo.web;

import la.ipo.admin.model.Resource;
import la.ipo.pagination.PageContext;
import la.ipo.service.AdminResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 2014年2月12日 下午4:34:44
 * @author WANGJUN
 *
 */
@Controller
@RequestMapping(value="/admin_resource")
public class AdminResourceController {

	@Autowired
	private AdminResourceService adminResourceService;
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(Model model) {
		PageContext pageContext = PageContext.getContext();
		pageContext.setPagination(false);
		model.addAttribute("resourceList", adminResourceService.findAll());
		model.addAttribute("resource", new Resource());
		return "admin_resource/add";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String processAdd(Resource resource) {
		adminResourceService.add(resource);
		return "redirect:/admin_resource/list";
	}
	
	/**
	 * 删除用户
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/delete")
	public String delete(Long id) throws Exception {
		adminResourceService.delete(id);
		return "redirect:/admin_resource/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model, Integer page) {
		PageContext pageContext = PageContext.getContext();
		pageContext.setCurrentPage(page == null ? 1 : page);
		pageContext.setPagination(true);
		model.addAttribute("resourceList", adminResourceService.findAll());
		model.addAttribute("pageContext", pageContext);
		return "admin_resource/list";
	}
	
}
