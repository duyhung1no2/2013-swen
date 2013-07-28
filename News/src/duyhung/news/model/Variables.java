package duyhung.news.model;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class Variables {
	public static final String ITEM = "item";
	public static final String TITLE = "title";
	public static final String LINK = "link";
	public static final String DESCRIPTION = "description";
	public static final String PUB_DATE = "pubDate";
	
	public static HashMap<String, List<NewsItem>> SAVED_NEWS_LIST = new HashMap<String, List<NewsItem>>(); 
	
//	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss tt");
//	public static final SimpleDateFormat DISPLAY_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm tt");
	
}
