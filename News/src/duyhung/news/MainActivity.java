package duyhung.news;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import duyhung.news.adapter.NewsPagerAdapter;

public class MainActivity extends FragmentActivity {

	private NewsPagerAdapter adapter;

	private ViewPager newsPager;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		newsPager = (ViewPager) findViewById(R.id.newsPager);

		adapter = new NewsPagerAdapter(getSupportFragmentManager());
		newsPager.setAdapter(adapter);
		newsPager.setOnPageChangeListener(onNewsPagerChangedListener);

		for (int i = 0; i < adapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(getResources().getStringArray(R.array.vne_categories)[i]).setTabListener(tabListener));
		}

	}

	private OnPageChangeListener onNewsPagerChangedListener = new ViewPager.SimpleOnPageChangeListener() {
		public void onPageSelected(int position) {
			actionBar.setSelectedNavigationItem(position);
		};
	};

	private TabListener tabListener = new TabListener() {

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			newsPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

	};


}