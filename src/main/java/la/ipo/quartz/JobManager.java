/**
 * 
 */
package la.ipo.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2014年2月21日 下午3:42:44
 * @author WANGJUN
 *
 */
@Component("jobManager")
public class JobManager {
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private QuartzJobSchedulingListener quartzJobSchedulingListener;
	
	/**
	 * 获取所有JOB
	 * 
	 * @return
	 */
	public List<JobDetailBean> getJobInfos() {
		List<CustomCronTrigger> triggers = quartzJobSchedulingListener.getCronTriggers();
		if(triggers != null) {
			List<JobDetailBean> jobDetails = new ArrayList<JobDetailBean>();
			for(CustomCronTrigger trigger : triggers) {
				jobDetails.add(trigger.getJobDetailBean());
			}
			return jobDetails;
		}
		return null;
	}

	public String getTriggerStatus(String triggerName) {
		String state = "未知";
		try {
			TriggerState triggerState = scheduler.getTriggerState(new TriggerKey(triggerName));
			System.out.println(triggerState);
			if (triggerState == Trigger.TriggerState.PAUSED) {  
				 System.out.println("触发器线程监听中。。。。,状态：暂停 ");  
				 state = "暂停";
	        } else if (triggerState == Trigger.TriggerState.NORMAL) {  
	            System.out.println("触发器线程监听中。。。。,状态：正常 ");  
	            state = "正常";
	        } else if (triggerState == Trigger.TriggerState.NONE) {  
	            System.out.println("触发器线程监听中。。。。,状态：没有触发器 ");
	            state = "没有该任务";
	        } else if (triggerState == Trigger.TriggerState.ERROR) {  
	            System.out.println("触发器线程监听中。。。。,状态：错误 ");
	            state = "错误";
	        } else if (triggerState == Trigger.TriggerState.BLOCKED) {  
	            System.out.println("触发器线程监听中。。。。,状态：阻塞 ");
	            state = "阻塞";
	        }  
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public void pauseTrigger(String triggerName) throws SchedulerException {
		scheduler.pauseTrigger(new TriggerKey(triggerName));
	}
	
	public void resumeTrigger(String triggerName) throws SchedulerException {
		scheduler.resumeTrigger(new TriggerKey(triggerName));
	}
	
	public void pauseJob(String jobName) throws SchedulerException {
		scheduler.pauseJob(new JobKey(jobName));
	}
	
	public void resumeJob(String jobName) throws SchedulerException {
		scheduler.resumeJob(new JobKey(jobName));
	}
	
	public void deleteJob(String jobName) throws SchedulerException {
		scheduler.deleteJob(new JobKey(jobName));
	}
	
	public void shutdown(boolean waitForJobsToComplete) throws SchedulerException {
		scheduler.shutdown(waitForJobsToComplete);
	}
}
