package duyhung.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import duyhung.news.adapter.CategoryAdapter;

public class MainActivity extends Activity{

	private ListView categoryListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		categoryListView = (ListView) findViewById(R.id.categoryListView);
		categoryListView.setAdapter(new CategoryAdapter(this, getResources().getStringArray(R.array.vne_categories)));
		categoryListView.setOnItemClickListener(listener);
	}

	private OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
			Intent i = new Intent(getApplicationContext(), NewsActivity.class);
			i.putExtra("POSITION", position);
			startActivity(i);			
		}
	};
}
