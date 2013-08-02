package duyhung.news;

import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleHtmlSerializer;
import org.htmlcleaner.TagNode;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import duyhung.news.model.Variables;

public class NewsFragment extends Fragment {

	private WebView newsWebView;
	private ProgressDialog progressDialog;

	public static final String IS_SELECTED = "IS_SELECTED";
	private String newsLink;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_news, container, false);
		newsWebView = (WebView) v.findViewById(R.id.newsWebView);
		newsWebView.getSettings().setDefaultTextEncodingName("utf-8");
		newsWebView.getSettings().setAllowContentAccess(true);

		newsLink = getArguments().getString(Variables.LINK);

		if (getArguments().getBoolean(IS_SELECTED)) {
			retrieveContent();
		}

		return v;
	}

	public void retrieveContent() {
		if (isAdded()) {
			progressDialog = ProgressDialog.show(getActivity(), "", "Đang tải nội dung ... ");
			new LoadWebTask().execute(newsLink);
		}
	}

	private class NewsWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			super.onPageFinished(view, url);
		}
	}

	private class LoadWebTask extends AsyncTask<String, Void, Void> {

		String content = "";

		@Override
		protected Void doInBackground(String... urls) {

			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			props.setAllowHtmlInsideAttributes(true);
			props.setAllowMultiWordAttributes(true);
			props.setRecognizeUnicodeChars(true);
			props.setOmitComments(true);

			try {
				TagNode rootNode = cleaner.clean(new URL(urls[0]).openStream());
				SimpleHtmlSerializer serializer = new SimpleHtmlSerializer(props);
				
				Object[] titleNode = rootNode.evaluateXPath("//h1[@class='Title']");
				content += serializer.getAsString((TagNode) titleNode[0]);
				
				Object[] dateNode = rootNode.evaluateXPath("//span[@class='spanTime']");
				content += serializer.getAsString((TagNode) dateNode[0]);
				
				Object[] contentNode = rootNode.evaluateXPath("//div[@class='fck_detail']");
				content += serializer.getAsString((TagNode) contentNode[0]);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			newsWebView.setWebViewClient(new NewsWebViewClient());
			newsWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
		}
	}

}
