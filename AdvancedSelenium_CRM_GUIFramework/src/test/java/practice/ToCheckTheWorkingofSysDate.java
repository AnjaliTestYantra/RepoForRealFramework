package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToCheckTheWorkingofSysDate {
	public static String getSystemDateYYYYMMDD() {
		Date date= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
		String d= simple.format(date);
		return d;
	}
	
	//Required System date
	public static String getReqSysDateYYYYMMDD(int days) {
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("YYYY-MM-dd");
		simple.format(dateObj);
		Calendar calendar = simple.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH,days);
	    String reqDate = simple.format(calendar.getTime());
		return reqDate;
	}
	public static void main(String[] args) {
		String startSysDate=ToCheckTheWorkingofSysDate.getSystemDateYYYYMMDD();
		String endSysDate= ToCheckTheWorkingofSysDate.getReqSysDateYYYYMMDD(30);
		
		System.out.println("startdate: "+startSysDate+"end date:"+endSysDate);
	}

}
