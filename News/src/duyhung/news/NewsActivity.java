package duyhung.news;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import duyhung.news.adapter.NewsAdapter;
import duyhung.news.dao.RssReader;
import duyhung.news.model.NewsItem;
import duyhung.news.model.Variables;

public class NewsActivity extends ListActivity {
	private List<NewsItem> newsList;
	private String linkRss;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		int position = getIntent().getExtras().getInt("POSITION");
		String categoryName = getResources().getStringArray(R.array.vne_categories)[position];
		setTitle(categoryName);
		linkRss = getResources().getStringArray(R.array.vne_rss)[position];

		if (Variables.SAVED_NEWS_LIST.containsKey(linkRss)) {
			newsList = Variables.SAVED_NEWS_LIST.get(linkRss);
			setListAdapter(new NewsAdapter(this, newsList));
		} else {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Loading news ... ");
			progressDialog.show();

			new RetrieveNewsList().execute();
		}

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String newsLink = ((NewsItem) l.getAdapter().getItem(position)).getLink();
		
		Intent newsDetailsIntent = new Intent(getApplicationContext(), NewsReadActivity.class);
		newsDetailsIntent.putExtra(Variables.LINK, newsLink);
		startActivity(newsDetailsIntent);
	}

	private class RetrieveNewsList extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			newsList = new RssReader().getNewsList(linkRss);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			getListView().setAdapter(new NewsAdapter(getApplicationContext(), newsList));
			Variables.SAVED_NEWS_LIST.put(linkRss, newsList);
			progressDialog.dismiss();
		}

	}

}
