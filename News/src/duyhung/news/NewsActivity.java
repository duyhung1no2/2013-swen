package duyhung.news;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import duyhung.news.dao.RssReader;
import duyhung.news.model.NewsItem;

public class NewsActivity extends ListActivity {
	private List<NewsItem> newsList;
	private String linkRss;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		linkRss = getResources().getStringArray(R.array.autopro_rss)[getIntent().getExtras().getInt("POSITION")];
		new RssReader().getNewsList(linkRss);
		new RetrieveNewsList().execute();

	}

	private class RetrieveNewsList extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			new RssReader().getNewsList(linkRss);
			getListView().setAdapter(new ArrayAdapter<NewsItem>(getApplicationContext(), android.R.layout.simple_list_item_1, newsList));
			return null;
		}

	}

}
