package duyhung.news.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import duyhung.news.NewsFragment;

public class NewsPagerAdapter extends FragmentPagerAdapter {

	public NewsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		NewsFragment newsFrag = new NewsFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(NewsFragment.ARG_POSITION, position);
		newsFrag.setArguments(bundle);
		return newsFrag;
	}

	@Override
	public int getCount() {
		return 14;
	}

}
