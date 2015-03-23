/**
 * 
 */
package la.ipo.web;

import java.util.List;

import la.ipo.admin.model.Role;
import la.ipo.admin.model.User;
import la.ipo.pagination.PageContext;
import la.ipo.service.AdminRoleService;
import la.ipo.service.AdminUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 2014年2月12日 下午4:34:44
 * @author WANGJUN
 *
 */
@Controller
@RequestMapping(value="/admin_user")
public class AdminUserController {

	@Autowired
	private AdminUserService userService;
	@Autowired
	private AdminRoleService adminRoleService;
	
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		return adminRoleService.findAll();
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "admin_user/edit";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String processAdd(User user, Long roleId) {
		userService.addUser(user, roleId);
		return "redirect:/admin_user/list";
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(Long id) {
		userService.deleteUser(id);
		return "redirect:/admin_user/list";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));
		return "admin_user/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(User user, Long roleId) {
		userService.updateUser(user, roleId);
		return "redirect:/admin_user/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model, Integer page) {
		PageContext pageContext = PageContext.getContext();
		pageContext.setCurrentPage(page == null ? 1 : page);
		pageContext.setPagination(true);
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("pageContext", pageContext);
		return "admin_user/list";
	}
}
