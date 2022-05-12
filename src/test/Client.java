package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Client {
	
	static PriorityQueue<AlertInstance> pq = new PriorityQueue<AlertInstance>(10);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alert[] alerts = queryAlerts();
		for(Alert alert : alerts) {
			pq.add(new AlertInstance(alert, Calendar.getInstance()));
		}
		try {
			runAlerts();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static Alert[] queryAlerts() {
		
		Alert[] alerts = {new Alert("high-latency", "latency_ms{service=foo}", 5, 7, "latency is too high"),
		                           new Alert("p999-latency", "latency_ms{service=foo}", 1, 6, "latency is too high"),
		                           new Alert("some_alertis", "latency_ms{service=foo}", 2, 8, "latency is too high")};
		return alerts;
	}
	private static void runAlerts() throws InterruptedException {
		while(true) {
			while(pq.peek().time.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
				Thread.sleep(10);
			}
			AlertInstance instance = pq.remove();
			Random r = new Random();
			if(instance.alert.threshold < r.nextInt(10)) {
				System.out.println(instance.alert.name + " " + pq.peek().time.getTimeInMillis() + " " + instance.alert.message);
			}
			else {
				System.out.println(instance.alert.name + " " + pq.peek().time.getTimeInMillis() + " " + "normal");
			}
            Calendar time = instance.time;
            time.setTimeInMillis(time.getTimeInMillis() + instance.alert.interval * 1000);
            pq.add(new AlertInstance(instance.alert, time));
		}
		
	}

}
