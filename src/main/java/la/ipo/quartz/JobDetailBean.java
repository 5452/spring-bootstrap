
package la.ipo.quartz;

import java.util.Map;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.DelegatingJob;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * 2014年2月21日 下午2:46:26
 * @author WANGJUN
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class JobDetailBean extends JobDetailImpl
		implements BeanNameAware, ApplicationContextAware, InitializingBean {

	private Class<?> actualJobClass;

	private String beanName;

	private ApplicationContext applicationContext;

	private String applicationContextJobDataKey;


	/**
	 * Overridden to support any job class, to allow a custom JobFactory
	 * to adapt the given job class to the Quartz Job interface.
	 * @see SchedulerFactoryBean#setJobFactory
	 */
	@Override
	public void setJobClass(Class jobClass) {
		if (jobClass != null && !Job.class.isAssignableFrom(jobClass)) {
			super.setJobClass(DelegatingJob.class);
			this.actualJobClass = jobClass;
		}
		else {
			super.setJobClass(jobClass);
		}
	}

	/**
	 * Overridden to support any job class, to allow a custom JobFactory
	 * to adapt the given job class to the Quartz Job interface.
	 */
	@Override
	public Class getJobClass() {
		return (this.actualJobClass != null ? this.actualJobClass : super.getJobClass());
	}

	/**
	 * Register objects in the JobDataMap via a given Map.
	 * <p>These objects will be available to this Job only,
	 * in contrast to objects in the SchedulerContext.
	 * <p>Note: When using persistent Jobs whose JobDetail will be kept in the
	 * database, do not put Spring-managed beans or an ApplicationContext
	 * reference into the JobDataMap but rather into the SchedulerContext.
	 * @param jobDataAsMap Map with String keys and any objects as values
	 * (for example Spring-managed beans)
	 * @see SchedulerFactoryBean#setSchedulerContextAsMap
	 */
	public void setJobDataAsMap(Map jobDataAsMap) {
		getJobDataMap().putAll(jobDataAsMap);
	}

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
	public String getBeanName() {
		return beanName;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void setApplicationContextJobDataKey(String applicationContextJobDataKey) {
		this.applicationContextJobDataKey = applicationContextJobDataKey;
	}


	@Override
	public void afterPropertiesSet() {
		if (getName() == null) {
			setName(this.beanName);
		}
		if (getGroup() == null) {
			setGroup(Scheduler.DEFAULT_GROUP);
		}
		if (this.applicationContextJobDataKey != null) {
			if (this.applicationContext == null) {
				throw new IllegalStateException(
					"JobDetailBean needs to be set up in an ApplicationContext " +
					"to be able to handle an 'applicationContextJobDataKey'");
			}
			getJobDataMap().put(this.applicationContextJobDataKey, this.applicationContext);
		}
	}

}
