package duyhung.news;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.vne_categories)));
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(getApplicationContext(), NewsActivity.class);
		i.putExtra("POSITION", position);
		startActivity(i);
	}

/*	public class RetrieveNewsList extends AsyncTask<String, Void, List<NewsItem>> {

		@Override
		protected List<NewsItem> doInBackground(String... params) {
			
			return null;
		}

	}
*/
}
