package duyhung.news.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import duyhung.news.NewsFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

	private List<NewsFragment> fragList;

	public CategoryPagerAdapter(FragmentManager fm, List<NewsFragment> list) {
		super(fm);
		this.fragList = list;
	}

	@Override
	public Fragment getItem(int position) {
		return fragList.get(position);
	}

	@Override
	public int getCount() {
		return fragList.size();
	}

}
