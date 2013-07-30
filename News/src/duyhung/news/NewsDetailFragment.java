package duyhung.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import duyhung.news.model.Variables;

public class NewsDetailFragment extends Activity {

	private WebView newsWebView;
	private ProgressDialog progressDialog;
	
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
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_news_detail);

		String newsLink = getIntent().getExtras().getString(Variables.LINK);

		newsWebView = (WebView) findViewById(R.id.newsWebView);
		newsWebView.getSettings().setAllowFileAccess(true);
		newsWebView.getSettings().setJavaScriptEnabled(true);

		progressDialog = ProgressDialog.show(this, "", "Loading news ... ");

/*		writeOutputFile();
		writeNewsFile(newsLink);
*/
		newsWebView.setWebViewClient(new NewsWebViewClient());
		newsWebView.loadUrl(newsLink);
//		newsWebView.loadUrl("file:///" + Environment.getExternalStorageDirectory() + "/" + OUTPUT_FILENAME);
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
