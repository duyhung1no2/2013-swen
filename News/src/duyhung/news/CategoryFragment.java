package duyhung.news;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import duyhung.news.adapter.NewsAdapter;
import duyhung.news.dao.XmlPullNewsParser;
import duyhung.news.model.NewsItem;
import duyhung.news.model.Variables;

public class CategoryFragment extends Fragment {

	private ListView newsListView;
	private Button loadMoreButton;
	private ProgressDialog progressDialog;

	private List<NewsItem> newsList;
	private String linkRss;

	public static final String ARG_POSITION = "ARG_POSITION";
	public static final int ITEM_PER_PAGE = 15;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		newsList = new ArrayList<NewsItem>();
		View v = inflater.inflate(R.layout.fragment_category, container, false);
		
		newsListView = (ListView) v.findViewById(R.id.newsListView);
		newsListView.setAdapter(new NewsAdapter(getActivity(), newsList));
		newsListView.setOnItemClickListener(onNewsItemClickListener);

		loadMoreButton = (Button) v.findViewById(R.id.loadMoreButton);
		loadMoreButton.setOnClickListener(onButtonClickListener);
		
		if (getArguments().getInt(ARG_POSITION) == 0) {
			retrieveNews();
		}
		return v;
	}

	protected OnItemClickListener onNewsItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
			Intent i = new Intent(getActivity(), NewsActivity.class);
			i.putExtra(Variables.LINK, getUrlArray(newsList));
			i.putExtra(Variables.SELECTED_ITEM_POSITION, position);
			startActivity(i);
		}
		
		private String[] getUrlArray(List<NewsItem> list){
			String[] urls = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				urls[i] = list.get(i).getLink();
			}
			return urls;
		}
	};

	public void retrieveNews() {
		if (isAdded()) {
			int position = getArguments().getInt(ARG_POSITION);
			linkRss = getResources().getStringArray(R.array.vne_rss)[position];

			if (Variables.SAVED_NEWS_LIST.containsKey(linkRss)) {
				newsList = Variables.SAVED_NEWS_LIST.get(linkRss);
				newsListView.setAdapter(new NewsAdapter(getActivity(), newsList));
			} else {
				progressDialog = new ProgressDialog(getActivity());
				progressDialog.setMessage("Đang tải tin tức ... ");
				progressDialog.show();

				new RetrieveNewsTask().execute();
			}
		}
	}
	
	private OnClickListener onButtonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tải tin tức ... ");
			progressDialog.show();
			new RetrieveMoreNewsTask().execute();
		}
	};

	private class RetrieveNewsTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			newsList = new XmlPullNewsParser().getNewsList(linkRss);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (isAdded()) {
				super.onPostExecute(result);
				newsListView.setAdapter(new NewsAdapter(getActivity(), newsList));
				Variables.SAVED_NEWS_LIST.put(linkRss, newsList);
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
		}
	}
	
	private class RetrieveMoreNewsTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			newsList = new XmlPullNewsParser().getMoreItem(linkRss, newsList, newsList.size(), newsList.size() + ITEM_PER_PAGE);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (isAdded()) {
				super.onPostExecute(result);
				newsListView.setAdapter(new NewsAdapter(getActivity(), newsList));
				newsListView.setSelection(newsList.size() - ITEM_PER_PAGE - 1);
				Variables.SAVED_NEWS_LIST.put(linkRss, newsList);
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
		}
	}
}
