package duyhung.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import duyhung.news.R;

public class CategoryAdapter extends ArrayAdapter<String> {
	
	private Context mContext;
	private String[] categories;

	public CategoryAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.mContext = context;
		this.categories = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.listview_item_category, parent, false);
		ImageView categoryIcon = (ImageView) v.findViewById(R.id.categoryTitleImageView);
		categoryIcon.setImageResource(R.drawable.vne_icon);
		TextView textView = (TextView) v.findViewById(R.id.categoryTitleTextView);
		textView.setText(categories[position]);

		return v;
	}

}
