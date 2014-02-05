package com.expense.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.expense.services.NotificationService;

public class NotificationJob extends QuartzJobBean {

	private NotificationService notificationService;

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		notificationService.sendBulkNotification(notificationService
				.fetchEligibleNotifications());
	}
}