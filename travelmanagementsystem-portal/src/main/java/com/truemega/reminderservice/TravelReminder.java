package com.truemega.reminderservice;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.truemega.logger.LoggerService;
import com.truemega.service.travelreminder.ReminderServiceInterface;
import com.truemega.utils.DateUtils;

@EnableAsync
@Configuration
@EnableScheduling
public class TravelReminder {
	private static LoggerService loggerService = new LoggerService();

	// prod 0 0 9 ? * MON
	// "0 * * * * ?" dev
	//@Scheduled(cron = "0 0 9 6 1/1 ?") prod
	@Scheduled(cron = "0 * * 16 1/1 ?")
	// "0 0 9 * * ?" "0 0 9 * * ?" "0 0/5 * * * ?") -- "0 * 17 * * ?"
	public void startTimer() {

		System.out
				.println("I'am Spring Reminder .............................");
		
		processAlert();
	}

	private void processAlert() {
		Context context = null;
		String strURL = "http://localhost:7001";
		try {
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, strURL);
			context = new InitialContext(props);
			System.out
					.println("Froooooooooooooooooom  LoooooooooooooooooooooooooookUP !!!!!!!!!");
			ReminderServiceInterface reminderServiceInterface = (ReminderServiceInterface) context
					.lookup("ejb/stateless/ReminderServiceInterface#"
							+ ReminderServiceInterface.class.getName());
			//reminderServiceInterface.sendUploadMandatoryFrom27To5();
		

		} catch (Exception e) {
			loggerService.logPortalError("", e);
		}
	}

}
