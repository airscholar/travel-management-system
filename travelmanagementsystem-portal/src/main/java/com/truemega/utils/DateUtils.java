package com.truemega.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static void getSundayOfThisWeekAndThePreviousWeek(Date fromDate, Date toDate) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.add(Calendar.DATE, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		toDate.setTime(c.getTime().getTime());

		c.add(Calendar.DATE, -6);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		fromDate.setTime(c.getTime().getTime());

	}
}
