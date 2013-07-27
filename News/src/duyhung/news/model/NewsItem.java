package duyhung.news.model;

import java.util.Date;

public class NewsItem {
	private String title;
	private String link;
	private String description;
	private Date pubDate;
	
	public NewsItem(){
		
	}

	public NewsItem(String title, String link, String description, Date pubDate) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

}
