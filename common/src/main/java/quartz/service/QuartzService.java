package quartz.service;

import org.quartz.JobDataMap;
import quartz.QuartzManager;
import quartz.job.TestJob;
import quartz.pojo.QuartzTestPojo;

public class QuartzService {
    public void startScheduleJob(){
        QuartzTestPojo pojo = new QuartzTestPojo();
        pojo.setId(1);
        pojo.setName("JobDataMap测试数据");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("testDataMap",pojo);
        QuartzManager.addJob("testJob",TestJob.class,"0/10 * * * * ?",jobDataMap);
    }

    public static void main(String[] args) {
        QuartzService quartzService = new QuartzService();
        quartzService.startScheduleJob();
    }
}
