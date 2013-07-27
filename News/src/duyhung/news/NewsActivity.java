package duyhung.news;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import duyhung.news.dao.RssReader;
import duyhung.news.model.NewsItem;

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
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Loading news ... ");
		progressDialog.show();
		new RetrieveNewsList().execute();

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
			getListView().setAdapter(new ArrayAdapter<NewsItem>(getApplicationContext(), android.R.layout.simple_list_item_1, newsList));
			progressDialog.dismiss();
		}

	}

}
