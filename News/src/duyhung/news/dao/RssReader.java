package duyhung.news.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import duyhung.news.model.NewsItem;
import duyhung.news.model.Variables;

public class RssReader {

	public List<NewsItem> getNewsList(String inputLink) {

		List<NewsItem> newsList = new ArrayList<NewsItem>();

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputLink);

			NodeList itemList = doc.getElementsByTagName(Variables.ITEM);
			if (itemList != null && itemList.getLength() > 0) {
				for (int i = 0; i < itemList.getLength(); i++) {
					Element crtElm = (Element) itemList.item(i);
					String title = crtElm.getElementsByTagName(Variables.TITLE).item(0).getTextContent();
					String link = crtElm.getElementsByTagName(Variables.LINK).item(0).getTextContent();
					String description = crtElm.getElementsByTagName(Variables.DESCRIPTION).item(0).getTextContent();
					String pubDate = crtElm.getElementsByTagName(Variables.PUB_DATE).item(0).getTextContent();

					newsList.add(new NewsItem(title, link, description, pubDate));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newsList;
	}

}
