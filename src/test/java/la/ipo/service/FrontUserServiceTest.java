package la.ipo.service;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import la.ipo.model.User;
import la.ipo.model.UserExample;

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
@TransactionConfiguration(transactionManager="transactionManagerFront", defaultRollback=true)
// @ActiveProfiles("mybatis")
public class FrontUserServiceTest {
	
	@Autowired
	private FrontUserService frontUserService;
	@Test
	public void getUserById() {
		User user = frontUserService.getUserById(1l);
		assertEquals("Wang Jun", user.getName());
	}

	@Test
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void getUserByParam() {
		UserExample example = new UserExample();
		example.createCriteria().andEmailLike("%@%");
		List<User> users = frontUserService.getUserByParam(example);
		assertEquals(2, users.size());
	}
	
	@Test
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void addUser() {
		User user = new User();
		user.setName("WANGJUN");
		user.setDisplayName("WANGJUN");
		user.setEmail("54524ever@gmail.com");
		user.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt()));
		user.setCreateDatetime(new Timestamp(new DateTime(2014, 4, 02, 17, 32).getMillis()));
		user.setCreateDatetime(new Timestamp(new DateTime().getMillis()));
		user.setMobile("");
		user.setAreaCode("");
		user.setIdType((byte) 1);
		user.setIdNo("");
		user.setStatus((byte) 1);
		user.setUserType((byte) 0);
		
		UserExample example = new UserExample();
		example.createCriteria().andNameLike("%W%");
		List<User> users = frontUserService.getUserByParam(example);
		int found = users.size();
		frontUserService.addUser(user);
		users = frontUserService.getUserByParam(example);
		assertEquals("验证新插入User", found + 1, users.size());
	}
	
}
