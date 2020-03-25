package com.flm.utils;

import java.util.Date;

public class DateUtils {
	
	public static double differenceInSeconds(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime())/1000;
	}

}
