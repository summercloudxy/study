package quartz;


import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Quartz调度管理器
 */
public class QuartzManager {

    public static Scheduler getStdSchedule() {
//        spring容器中获取
//        ApplicationContext context = ApplicationContextListener.getApplicationContext();
//        StdScheduler scheduler = (StdScheduler) context.getBean("quartzScheduler");
//        return scheduler;
        Scheduler defaultScheduler = null;
        try {
            defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return defaultScheduler;
    }


    /**
     * 添加一个定时任务
     *
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @param cls
     * @param cronExpression
     */
    public static void addJob(String jobName, String jobGroup, String triggerName, String triggerGroup,
                              @SuppressWarnings("rawtypes") Class cls, String cronExpression) {
        try {
            Scheduler scheduler = getStdSchedule();
            JobDetail jobDetail = newJob().withIdentity(JobKey.jobKey(jobName, jobGroup)).ofType(cls).build();
            // 触发器
            CronTrigger trigger = newTrigger().withIdentity(TriggerKey.triggerKey(triggerName, triggerGroup))
                    .withSchedule(cronSchedule(cronExpression)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addJob(String jobName, String jobGroup, String triggerName, String triggerGroup,
                              @SuppressWarnings("rawtypes") Class cls, String cronExpression, JobDataMap jobDataMap) {
        try {
            Scheduler scheduler = getStdSchedule();
            JobDetail jobDetail = newJob().setJobData(jobDataMap).withIdentity(JobKey.jobKey(jobName, jobGroup)).ofType(cls).build();
            // 触发器
            CronTrigger trigger = newTrigger().withIdentity(TriggerKey.triggerKey(triggerName, triggerGroup))
                    .withSchedule(cronSchedule(cronExpression)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 添加定时任务
     *
     * @param jobName
     * @param triggerName
     * @param jobClass
     * @param cronExpression
     */
    public static void addJob(String jobName, String triggerName, @SuppressWarnings("rawtypes") Class jobClass,
                              String cronExpression) {
        addJob(jobName, null, triggerName, null, jobClass, cronExpression);
    }

    public static void addJob(String jobName, @SuppressWarnings("rawtypes") Class jobClass, String cronExpression) {
        addJob(jobName, null, jobName, null, jobClass, cronExpression);
    }


    public static void addJob(String jobName, @SuppressWarnings("rawtypes") Class jobClass, String cronExpression, JobDataMap jobDataMap) {
        addJob(jobName, null, jobName, null, jobClass, cronExpression, jobDataMap);
    }


    public static void addJobWithMinutes(String jobName, String jobGroup, String triggerName, String triggerGroup,
                                         Class cls, int interval, JobDataMap jobDataMap) {
        try {
            Scheduler scheduler = getStdSchedule();
            JobDetail jobDetail;
            if (jobDataMap != null) {
                jobDetail = newJob().setJobData(jobDataMap).withIdentity(JobKey.jobKey(jobName, jobGroup)).ofType(cls)
                        .build();
            } else {
                jobDetail = newJob().withIdentity(JobKey.jobKey(jobName, jobGroup)).ofType(cls).build();
            }
            // 触发器
            SimpleTrigger trigger = newTrigger().withIdentity(TriggerKey.triggerKey(triggerName, triggerGroup))
                    .withSchedule(simpleSchedule().withIntervalInMinutes(interval).repeatForever()).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 修改一个任务的触发时间
     *
     * @param jobName
     * @param time
     */
    @SuppressWarnings("rawtypes")
    public static void modifyJobTime(String jobName, String time) {
        try {
            Scheduler scheduler = getStdSchedule();
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(TriggerKey.triggerKey(jobName));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName));
                Class objJobClass = jobDetail.getJobClass();
                removeJob(jobName);
                addJob(jobName, objJobClass, time);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改一个任务的触发时间
     *
     * @param triggerName
     * @param triggerGroupName
     * @param time
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
        try {
            Scheduler scheduler = getStdSchedule();
            CronTriggerImpl trigger =
                    (CronTriggerImpl) scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                // 修改时间
                trigger.setCronExpression(time);
                // 重启触发器
                scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务
     *
     * @param jobName
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler scheduler = getStdSchedule();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName));// 停止触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName));// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler scheduler = getStdSchedule();
            scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));// 停止触发器
            scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler scheduler = getStdSchedule();
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler scheduler = getStdSchedule();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 检查任务是否存在
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static boolean checkExists(String jobName, String jobGroup) {
        boolean exists;
        try {
            Scheduler scheduler = getStdSchedule();
            exists = scheduler.checkExists(new JobKey(jobName, jobGroup));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    public static JobDataMap getJobDataMap(String jobName, String jobGroup) {
        JobDataMap jobDataMap;
        try {
            Scheduler scheduler = getStdSchedule();
            jobDataMap = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup)).getJobDataMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jobDataMap;

    }
}
