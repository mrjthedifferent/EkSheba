package bd.gov.eksheba.eksheba;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jewel on 7/30/2017.
 */

public class MyAppWebViewClient extends WebViewClient {

    // variable for onReceivedError
    private boolean refreshed;

    // handling external links as intents
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        super.shouldOverrideUrlLoading(view, url);

        if( Uri.parse(url).getHost().endsWith("eksheba.gov.bd") ) {
           return false;
        }
        if( Uri.parse(url).getHost().endsWith("forms.gov.bd") ) {
            return false;
        }

        if (url.contains(".pdf") || url.contains(".PDF")){
            String[] spliturl = url.split("http://someurl/");
            String googleurl = "http://docs.google.com/viewer?embedded=true&url=";
            System.out.println("Google Url"+googleurl);
            System.out.println("spliturl"+spliturl[1]);
            view.loadUrl(googleurl+spliturl[1]);
        }

        if(url.startsWith("tel:")){

        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

    // refresh on connection error (sometimes there is an error even when there is a network connection)
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if(!refreshed) {
            view.loadUrl(failingUrl);
            // when network error is real do not reload url again
            refreshed = true;
        }
        if (errorCode == -10) {
            System.out.println("Error occured: Error code: "
                    + errorCode + "..Ignoring this error");
        }
        else
        {
            // Show your custom screen to notify error has occured
        }
    }

}