package in.co.dreamsdoor.covid19india;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import in.co.dreamsdoor.covid19india.utils.BaseActivity;
import in.co.dreamsdoor.covid19india.utils.Config;

public class AboutActivity extends BaseActivity {


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView aboutwebView = new WebView(this);

        aboutwebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        aboutwebView.setWebViewClient(new WebViewClient() {


            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        aboutwebView.loadUrl((new Config().getDEVELOPERS_WEBSITE()));
        setContentView(aboutwebView);
    }
}
