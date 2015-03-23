/**
 * 
 */
package la.ipo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import la.ipo.admin.model.User;
import la.ipo.service.AdminResourceService;
import la.ipo.service.AdminUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 2014年2月12日 下午4:34:44
 * @author WANGJUN
 *
 */
@Controller
@RequestMapping("/")
@SessionAttributes("user")
public class AuthController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminResourceService resourceService;
	@Autowired
	private AuthenticationManager customAuthenticationManager;

	@RequestMapping("/")
	public String index(Model model, HttpSession session) {
		if(session == null || session.getAttribute("user") == null)
			return "redirect:/login";
		User user = (User) session.getAttribute("user");
		model.addAttribute("resources", resourceService.getUserResourcesByUserId(user.getId()));
		return "index";
	}
	
	/**
	 * 进入登录页面, 如果处于登陆状态，则先执行注销过程
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if(session != null && session.getAttribute("user") != null)
			return "redirect:/";
		return "login";
	}
	
	/**
	 * 登陆
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(String email, String password, Model model, HttpServletRequest request) {
		try {
			// 验证用户账号与密码是否正确
			User user = adminUserService.authUser(email, password);
			Authentication authentication = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		    // 当验证都通过后，把用户信息放在session里
			model.addAttribute("user", user);
		} catch (AuthenticationException ae) {
			request.setAttribute("error", "登录异常，请联系管理员！");
		    return "login";
		}
		return "redirect:/";
	}
	
	/**
	 * 登出
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}
}
