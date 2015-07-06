package uk.aparkermedia.gizalaugh;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Arron on 01/07/2015.
 */
public class WebAppInterface extends ActionBarActivity {
    Context mContext;

    WebAppInterface Android;
    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void playVideo(String videoUrl)
    {
        Intent i = new Intent(mContext, VideoPlayer.class);
        i.putExtra("url", videoUrl);
        mContext.startActivity(i);
        finish();
    }
}
