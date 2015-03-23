/**
 * 
 */
package la.ipo.quartz.example;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 2014年2月21日 下午2:07:45
 * 
 * @author WANGJUN <code>@QuartzJob</code>声明生命周期为prototype，所以不必担心线程安全问题。
 */
//@QuartzJob(name = "FirstJob", displayName = "第一个定时任务", description = "第一个定时任务，线程安全", cronExp = "*/10 * * * * ?")
//@DisallowConcurrentExecution
//@PersistJobDataAfterExecution
public class FirstJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("FirstJob is running@" + this.hashCode() + ":" + new DateTime().toString("yyyy-MM-dd hh:mm:ss"));
	}

}
