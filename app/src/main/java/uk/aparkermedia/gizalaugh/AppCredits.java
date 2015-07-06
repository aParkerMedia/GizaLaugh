package uk.aparkermedia.gizalaugh;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;
import android.widget.ProgressBar;
import android.widget.TextView;


public class AppCredits extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_credits);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.gizalaugh_logo2);

    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_app_credits, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.


            //noinspection SimplifiableIfStatement
            switch (item.getItemId()) {
                case R.id.action_twitter:
                    String url = "https://www.twitter.com/gizalaughtv";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    break;
                case R.id.action_facebook:
                    String url1 = "https://www.facebook.com/gizalaughtv";
                    Intent i1 = new Intent(Intent.ACTION_VIEW);
                    i1.setData(Uri.parse(url1));
                    startActivity(i1);
                    break;
                case R.id.action_youtube:
                    String url2 = "https://www.youtube.com/channel/UCUu94nkQjG1NgnHRDpQ5Kvg";
                    Intent i2 = new Intent(Intent.ACTION_VIEW);
                    i2.setData(Uri.parse(url2));
                    startActivity(i2);
                    break;

            }


            return super.onOptionsItemSelected(item);
        }

    }


