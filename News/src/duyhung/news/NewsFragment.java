package duyhung.news;

import android.app.ProgressDialog;
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
		newsLink = getArguments().getString(Variables.LINK);

		if(getArguments().getBoolean(IS_SELECTED)){
			retrieveContent();
		}

		return v;
	}

	public void retrieveContent() {
		if (isAdded()) {
			progressDialog = ProgressDialog.show(getActivity(), "", "Đang tải nội dung ... ");
			newsWebView.setWebViewClient(new NewsWebViewClient());
			newsWebView.loadUrl(newsLink);

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
	
//	private class LoadWebTask extends AsyncTask<String, Void, Void> {
//		@Override
//		protected Void doInBackground(String... urls) {
//			HtmlCleaner cleaner = new HtmlCleaner();
//			TagNode node = null;
//			String content = "";
//			try {
//				node = cleaner.clean(new URL(urls[0]).openStream());
//				
//				Object[] contentNode = node.evaluateXPath("//html/body/div[id='wrap']/div[id='content']/div[class='content-center']/div[class='content']/div[class='cxtLeft']/div[class='fck_detail']/");
//				for (int i = 0; i < contentNode.length; i++) {
////					NodeList childNode = contentNode
////							item(i).getChildNodes();
////					content += contentNode[i].get
//				}
//						
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return null;
//		}
//	}

}
