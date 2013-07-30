package duyhung.news.adapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import duyhung.news.R;
import duyhung.news.model.NewsItem;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

	private Context mContext;
	private List<NewsItem> newsList;
	private static HashMap<String, Bitmap> savedImage = new HashMap<String, Bitmap>();
	
	public NewsAdapter(Context context, List<NewsItem> newsList) {
		super(context, R.layout.listview_item_news, newsList);
		this.mContext = context;
		this.newsList = newsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.listview_item_news, parent, false);

		NewsItem item = newsList.get(position);
		String imageUrl = getImageUrl(item.getDescription());

		ImageView categoryIcon = (ImageView) v.findViewById(R.id.newsTitleImageView);
		TextView titleTextView = (TextView) v.findViewById(R.id.newsTitleTextView);
		TextView timeTextView = (TextView) v.findViewById(R.id.newsTimeTextView);
		ViewHolder holder = new ViewHolder(categoryIcon, imageUrl);
		v.setTag(holder);
		
		if(savedImage.containsKey(imageUrl)){
            holder.image.setImageBitmap(savedImage.get(imageUrl));
		} else {
			new LoadViewHolderTask().execute(holder);
		}
		
		categoryIcon.getLayoutParams().width = 60;
		categoryIcon.getLayoutParams().height = 40;
		titleTextView.setText(item.getTitle());
		timeTextView.setText(item.getPubDate());

		return v;
	}

	private String getImageUrl(String desc) {
		Matcher matcher = Pattern.compile("<img.*?src=\"(.*?)\".*?>").matcher(desc);
		if (matcher.find())
			return matcher.group(1);
		return "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/372810_262700667105773_1895213017_q.jpg";
	}

	private class LoadViewHolderTask extends AsyncTask<ViewHolder, Void, Bitmap>{

		private ViewHolder v;

	    @Override
	    protected Bitmap doInBackground(ViewHolder... params) {
	        v = params[0];
	        try {
				return BitmapFactory.decodeStream(new URL(v.url).openStream());
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	    }

	    @Override
	    protected void onPostExecute(Bitmap result) {
	        super.onPostExecute(result);
            v.image.setVisibility(View.VISIBLE);
            v.image.setImageBitmap(result);
            savedImage.put(v.url, result);
	    }
		
	}

	private class ViewHolder {
		private ImageView image;
		private String url;
		
		public ViewHolder(ImageView image, String url) {
			super();
			this.image = image;
			this.url = url;
		}
	}
}
