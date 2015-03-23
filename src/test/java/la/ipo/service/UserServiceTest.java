package la.ipo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import la.ipo.admin.model.User;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
// @ActiveProfiles("mybatis")
public class UserServiceTest {
	
	@Autowired
	private AdminUserService userService;
	@Test
	public void getUserById() {
		User user = userService.getUserById(1l);
		assertEquals("Wang", user.getName());
	}

	@Test
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void getUserByParam() {
		User user = new User("Jun");
		List<User> users = userService.getUserByParam(user);
		assertEquals(1, users.size());
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void addUser() {
		User user = new User("Wang Jun");
		user.setEmail("test1@test1.com");
		user.setPassword(BCrypt.hashpw("wangjun", BCrypt.gensalt()));
		user.setUpdateDateTime(new DateTime());
		List<User> users = userService.getUserByParam(user);
		int found = users.size();
		userService.addUser(user, 2l);
		users = userService.getUserByParam(user);
		assertEquals("验证新插入User", found + 1, users.size());
	}
	
	@Test
	public void authUser() {
		try {
			User user = userService.authUser("wangjun@pe.vc", "wangjun");
			assertEquals("wangjun@pe.vc", user.getEmail());
		} catch (Exception e) {
			assertEquals("登陆失败，Email或密码错误, email:wangjun@pe.vc, password:wangjun", e.getMessage());
		}
	}
}
