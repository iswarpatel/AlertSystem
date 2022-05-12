package test;

public class Alert {
	String name;
	String query;
	int interval;
	int threshold;
	String message;

	public Alert(String name, String query, int interval, int threshold, String message) {
		this.interval = interval;
		this.message = message;
		this.threshold = threshold;
		this.name = name;
		this.query = query;
	}

}
