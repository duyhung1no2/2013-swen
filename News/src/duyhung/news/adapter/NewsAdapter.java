package duyhung.news.adapter;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

		String desc = item.getDescription();
		String imageUrl = getImageUrl(desc);
		Bitmap imageBitmap = null;
		try {
			imageBitmap = BitmapFactory.decodeStream(new URL(imageUrl).openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageView categoryIcon = (ImageView) v.findViewById(R.id.newsTitleImageView);
		categoryIcon.getLayoutParams().width = 60;
		categoryIcon.getLayoutParams().height = 40;
		categoryIcon.setImageBitmap(imageBitmap);

		TextView titleTextView = (TextView) v.findViewById(R.id.newsTitleTextView);

		titleTextView.setText(item.getTitle());

		TextView timeTextView = (TextView) v.findViewById(R.id.newsTimeTextView);
		timeTextView.setText(item.getPubDate());

		return v;
	}

	private String getImageUrl(String desc) {
		Matcher matcher = Pattern.compile("<img.*?src=\"(.*?)\".*?>").matcher(desc);
		if (matcher.find())
			return matcher.group(1);
		return "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc1/372810_262700667105773_1895213017_q.jpg";
	}

}
