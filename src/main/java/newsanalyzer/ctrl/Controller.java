package newsanalyzer.ctrl;

import newsapi.NewsApi;

public class Controller {

	public static final String APIKEY = "c3eab09b0d924cfcb717b83c823b8671";

	public void process() {
		System.out.println("Start process");

		//TODO implement Error handling
		try {

		}
		catch (Exception e) {
			System.out.println("Something went wrong.");
		}

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}
	

	public Object getData() {
		return null;
	}
}
