package la.ipo.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import la.ipo.quartz.annotation.QuartzJob;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;

public class QuartzJobSchedulingListener implements ApplicationListener<ContextRefreshedEvent>
{    
    @Autowired
    private Scheduler scheduler;
    
    private List<CustomCronTrigger> cronTriggers;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        try
        {
            ApplicationContext applicationContext = event.getApplicationContext();
            cronTriggers = this.loadCronTriggerBeans(applicationContext);
            this.scheduleJobs(cronTriggers);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private List<CustomCronTrigger> loadCronTriggerBeans(ApplicationContext applicationContext)
    {
        Map<String, Object> quartzJobBeans = applicationContext.getBeansWithAnnotation(QuartzJob.class);
        Set<String> beanNames = quartzJobBeans.keySet();
        List<CustomCronTrigger> cronTriggerBeans = new ArrayList<CustomCronTrigger>();
        for (String beanName : beanNames)
        {
            CustomCronTrigger cronTriggerBean = null;
            Object object = quartzJobBeans.get(beanName);
            System.out.println(object);
            try {
                cronTriggerBean = this.buildCronTriggerBean(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(cronTriggerBean != null)
            {
                cronTriggerBeans.add(cronTriggerBean);
            }
        }
        return cronTriggerBeans;
    }
    
    public CustomCronTrigger buildCronTriggerBean(Object job) throws Exception
    {
        CustomCronTrigger cronTriggerBean = null;
        QuartzJob quartzJobAnnotation = AnnotationUtils.findAnnotation(job.getClass(), QuartzJob.class);
        if(Job.class.isAssignableFrom(job.getClass()))
        {
            System.out.println("It is a Quartz Job");
            cronTriggerBean = new CustomCronTrigger();
            cronTriggerBean.setCronExpression(quartzJobAnnotation.cronExp());                
            cronTriggerBean.setBeanName(quartzJobAnnotation.name()+"_trigger");
            JobDetailBean jobDetail = new JobDetailBean();
            jobDetail.setBeanName(quartzJobAnnotation.displayName());
            jobDetail.setDescription(quartzJobAnnotation.description());
            jobDetail.setName(quartzJobAnnotation.name());
            jobDetail.setJobClass(job.getClass());
            cronTriggerBean.setJobDetail(jobDetail);  
            cronTriggerBean.setJobDetailBean(jobDetail);
            cronTriggerBean.afterPropertiesSet();
        }
        else
        {
            throw new RuntimeException(job.getClass()+" doesn't implemented "+Job.class);
        }
        return cronTriggerBean;
    }
    
    protected void scheduleJobs(List<CustomCronTrigger> cronTriggerBeans)
    {
        for (CustomCronTrigger cronTriggerBean : cronTriggerBeans) {
            JobDetail jobDetail = cronTriggerBean.getJobDetail();
            try {
                scheduler.scheduleJob(jobDetail, cronTriggerBean.getObject());
            } catch (SchedulerException e) {
                e.printStackTrace();
            } 
        }
    }

	public List<CustomCronTrigger> getCronTriggers() {
		return cronTriggers;
	}
}