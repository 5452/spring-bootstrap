/**
 * 
 */
package la.ipo.quartz;

import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * 2014年2月21日 下午1:59:36
 * @author WANGJUN
 *
 */
public class CustomCronTrigger extends CronTriggerFactoryBean {

	private JobDetailBean jobDetailBean = null;
	
	private JobDetail jobDetail;
	
	public JobDetailBean getJobDetailBean() {
		return jobDetailBean;
	}
	
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetailBean(JobDetailBean jobDetail) {
		super.setJobDetail(jobDetail);
		this.jobDetailBean = jobDetail;
	}
	
	public void setJobDetail(JobDetail jobDetail) {
		super.setJobDetail(jobDetail);
		this.jobDetail = jobDetail;
	}


}
