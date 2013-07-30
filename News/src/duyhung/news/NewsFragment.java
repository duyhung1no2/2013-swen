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
	
/*	private static String FILENAME = "";
	private static final String OUTPUT_FILENAME = "NEWS_OUTPUT.html";
	private static String OUTPUT = "<html>" +
												"<head>" +
													"<script src=\"http://code.jquery.com/jquery-1.9.1.js\"></script>" +
													"<script>$(document).ready(function(){" +
														"$('.spanTime').load('" + FILENAME + " .spanTime');" +
														"$('.title').load('" + FILENAME + " .title');" +
														"$('.fck_detail').load('" + FILENAME + " .fck_detail');});" +
													"</script>" +
												"</head>" +
												"<body>" +
													"<div class=\"title\"></div>" +
													"<div class=\"spanTime\" style=\"font-style:italic;\"></div>" +
													"<div class=\"fck_detail\"></div>" +
												"</body>" +
											"</html>"; 
*/
	
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

//			writeOutputFile();
//			writeNewsFile(newsLink);
//			newsWebView.loadUrl("file:///" + Environment.getExternalStorageDirectory() + "/" + OUTPUT_FILENAME);
		}
	}

/*	private void writeNewsFile(String newsLink) {
		FILENAME = Uri.parse(newsLink).getLastPathSegment();
		File file = new File(Environment.getExternalStorageDirectory() + "/", FILENAME);
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		InputStream inStream = null;
		URL url = null;
		try {
			url = new URL(newsLink);

			inStream = url.openStream();
			byte[] byteChunk = new byte[4096];
			int n;

			while ((n = inStream.read(byteChunk)) > 0) {
				bais.write(byteChunk, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			FileOutputStream outStream = new FileOutputStream(file);
			outStream.write(bais.toByteArray());
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeOutputFile() {
		File f = new File(Environment.getExternalStorageDirectory() + "/" + OUTPUT_FILENAME);
		try {
			FileOutputStream outStream = new FileOutputStream(f);
			outStream.write(OUTPUT.getBytes());
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	
	private class NewsWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}
			super.onPageFinished(view, url);
		}
	}

}
