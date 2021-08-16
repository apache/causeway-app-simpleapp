package domainapp.webapp.quartz;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import lombok.val;

import domainapp.webapp.quartz.job.SampleJob;

@Configuration
@ComponentScan
public class QuartzModule {

    private static final int REPEAT_INTERVAL_SECS = 60;
    private static final int START_DELAY_SECS = 20;
    private static final int MILLIS_PER_SEC = 1000;

    @Bean
    public JobDetailFactoryBean jobDetail() {
        val jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(SampleJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        val trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setStartDelay(START_DELAY_SECS * MILLIS_PER_SEC);
        trigger.setRepeatInterval(REPEAT_INTERVAL_SECS * MILLIS_PER_SEC);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }
}
