package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	//random number
	public int generateRandomNum() {
		Random ran= new Random();
		int r=ran.nextInt(5000);
		return r;
	}
	
	//system date
	public String getSystemDateYYYYMMDD() {
		Date date= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
		String d= simple.format(date);
		return d;
	}
	
	//Required System date
	public String getReqSysDateYYYYMMDD(int days) {
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("YYYY-MM-dd");
		simple.format(dateObj);
		Calendar calendar = simple.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH,days);
	    String reqDate = simple.format(calendar.getTime());
		return reqDate;
		
		
	}

}
