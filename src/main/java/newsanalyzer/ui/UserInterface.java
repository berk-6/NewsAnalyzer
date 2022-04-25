package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

import static newsanalyzer.ctrl.Controller.APIKEY;


public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1() {
		System.out.println("Sport");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("sport")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.sports)
				.setExcludeDomains("www.Lifehacker.com")
				.createNewsApi();
		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
		ctrl.process();
	}

	public void getDataFromCtrl2(){
		System.out.println("Wissenschaft");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Wissenschaft")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setExcludeDomains("www.newbusiness.at/")
				.setSourceCategory(Category.science)
				.createNewsApi();
		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
		ctrl.process();
	}

	public void getDataFromCtrl3(){
		System.out.println("Technologie");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("Technologie")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setExcludeDomains("www.Lifehacker.com")
				.setSourceCategory(Category.technology)
				.createNewsApi();

		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
		ctrl.process();

	}
	
	public void getDataForCustomInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Write down your topic: ");
		String s = sc.next();
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(s)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();

		NewsReponse newsResponse = newsApi.getNews();
		if(newsResponse != null) {
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
		ctrl.process();
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Sport", this::getDataFromCtrl1);
		menu.insert("b", "Wissenschaft", this::getDataFromCtrl2);
		menu.insert("c", "Technologie", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
