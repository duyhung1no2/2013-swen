package duyhung.news.adapter;

import java.util.List;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import duyhung.news.CategoryFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {

	private List<CategoryFragment> fragList;

	public CategoryPagerAdapter(FragmentManager fm, List<CategoryFragment> list) {
		super(fm);
		this.fragList = list;
	}

	@Override
	public CategoryFragment getItem(int position) {
		return fragList.get(position);
	}

	@Override
	public int getCount() {
		return fragList.size();
	}

}
