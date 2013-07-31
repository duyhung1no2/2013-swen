package duyhung.news;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import duyhung.news.adapter.NewsPagerAdapter;
import duyhung.news.model.Variables;

public class NewsActivity extends FragmentActivity {

	private ViewPager pager;
	
	private List<NewsFragment> fragments;
	private String[] urls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		setTitle("Đọc tin");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		pager = (ViewPager) findViewById(R.id.newsPager);
		
		urls = getIntent().getExtras().getStringArray(Variables.LINK);
		fragments = new ArrayList<NewsFragment>();

		for (int i = 0; i < urls.length; i++) {
			NewsFragment newsFrag = new NewsFragment();
			Bundle bundle = new Bundle();
			bundle.putString(Variables.LINK, urls[i]);
			if(getIntent().getExtras().getInt(Variables.SELECTED_ITEM_POSITION) == i){
				bundle.putBoolean(NewsFragment.IS_SELECTED, true);
			} else{
				bundle.putBoolean(NewsFragment.IS_SELECTED, false);
			}
			newsFrag.setArguments(bundle);
			fragments.add(newsFrag);
		}
		
		NewsPagerAdapter adapter = new NewsPagerAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(onPagerChangedListener);
		pager.setCurrentItem(getIntent().getExtras().getInt(Variables.SELECTED_ITEM_POSITION));
	}

	private OnPageChangeListener onPagerChangedListener = new ViewPager.SimpleOnPageChangeListener(){
		public void onPageSelected(int position) {
			fragments.get(position).retrieveContent();
		};
	};
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			break;
		}

		return true;
	};

}
