package quartz.job;

import org.quartz.*;
import quartz.pojo.QuartzTestPojo;

import java.util.Date;

public class TestJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        QuartzTestPojo pojo = (QuartzTestPojo) jobDataMap.get("testDataMap");
        System.out.println(new Date()+":"+pojo);
    }
}
