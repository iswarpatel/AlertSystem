package test;

import java.util.Calendar;

public class AlertInstance implements Comparable<AlertInstance>{
	Alert alert;
	Calendar time;

	public AlertInstance(Alert alert, Calendar time) {
		// TODO Auto-generated constructor stub
		this.alert = alert;
		this.time = time;	
	}
	public int compareTo(AlertInstance o) {        
	      return this.time.compareTo(o.time);
   }
}
