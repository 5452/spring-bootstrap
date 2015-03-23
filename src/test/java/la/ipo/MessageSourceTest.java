/**
 * 
 */
package la.ipo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import la.ipo.admin.model.User;
import la.ipo.admin.mybatis.UserMapper;

import org.joda.time.DateTime;
import org.quartz.SchedulerException;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 2014年1月24日 下午12:08:33
 * 
 * @author WANGJUN
 * 
 */
public class MessageSourceTest {

	// private static MessageSource resources;
	private static GenericXmlApplicationContext ctx;

	/**
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws SchedulerException {
		/*
		 * resources = new
		 * ClassPathXmlApplicationContext("classpath:spring/mvc-core-config.xml"
		 * ); String message = resources.getMessage("header.home", null, null);
		 * System.out.println(message);
		 */

		ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("mybatis");
		ctx.load("classpath:spring/root-context.xml");
		ctx.refresh();
		UserMapper dao = (UserMapper) ctx.getBean("userMapper");

		User user = new User();
		user.setName("5452");
		user.setEmail("wangjun@pe.vc");
		user.setPassword("password");
		user.setCreateDateTime(new DateTime(2014, 2, 10, 12, 20, 30));
		user.setUpdateDateTime(new DateTime(2014, 2, 12, 11, 0));

		List<User> users = dao.getByParams(user);
		dao.add(user);

		user = dao.getById(1l);
		assertEquals("Wang", user.getName());
		user = new User("xnew");
		users = dao.getByParams(user);
		assertEquals(0, users.size());
		dao.delete(21l);
		/*
		 * JobManager jobManager = (JobManager)ctx.getBean("jobManager");
		 * List<JobDetailBean> jobs = jobManager.getJobInfos();
		 * for(JobDetailBean job : jobs) { System.out.println(job.getBeanName()
		 * + ":" + job.getDescription()); } System.out.println("status:" +
		 * jobManager.getTriggerStatus("FirstJob_trigger"));
		 * //jobManager.shutdown(true); System.out.println("status:" +
		 * jobManager.getTriggerStatus("FirstJob_trigger"));
		 */
		/*
		 * try { userService.authUser("wangjun@pe.vc", "wangjun"); } catch
		 * (Exception e) { System.out.println(e.getMessage());
		 * e.printStackTrace(); } System.exit(0);
		 */
		
		System.exit(0);
	}
}
