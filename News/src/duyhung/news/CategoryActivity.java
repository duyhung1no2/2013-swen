package duyhung.news;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import duyhung.news.adapter.CategoryPagerAdapter;

public class CategoryActivity extends FragmentActivity {

	private ViewPager pager;
	private ActionBar actionBar;

	private CategoryPagerAdapter adapter;
	private List<CategoryFragment> fragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		pager = (ViewPager) findViewById(R.id.categoryPager);

		fragmentList = new ArrayList<CategoryFragment>() {
			private static final long serialVersionUID = 1L;
			{
				for (int i = 0; i < getResources().getStringArray(R.array.vne_categories).length; i++) {
					CategoryFragment cateFrag = new CategoryFragment();
					Bundle bundle = new Bundle();
					bundle.putInt(CategoryFragment.ARG_POSITION, i);
					cateFrag.setArguments(bundle);
					add(cateFrag);
				}
			}
		};
		adapter = new CategoryPagerAdapter(getSupportFragmentManager(), fragmentList);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(onNewsPagerChangedListener);

		for (int i = 0; i < adapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(getResources().getStringArray(R.array.vne_categories)[i]).setTabListener(tabListener));
		}
	}

	private OnPageChangeListener onNewsPagerChangedListener = new ViewPager.SimpleOnPageChangeListener() {
		public void onPageSelected(int position) {
			actionBar.setSelectedNavigationItem(position);
			fragmentList.get(position).retrieveNews();
		};
	};

	private TabListener tabListener = new TabListener() {

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			pager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

	};

}