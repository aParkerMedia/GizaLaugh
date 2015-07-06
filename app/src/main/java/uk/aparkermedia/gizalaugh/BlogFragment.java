package uk.aparkermedia.gizalaugh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by Arron on 05/07/2015.
 */
public class BlogFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "0";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    private static WebView myWebView;
    ProgressBar loadingProgressBar;
    private Uri videoUri;


    public static BlogFragment newInstance(int sectionNumber) {
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public BlogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blog, container, false);


        if (!DetectConnection.checkInternetConnection(getActivity())) {
            Intent intent = new Intent(getActivity(), NoInternet.class);
            startActivity(intent);
            getActivity().finish();
        } else {

        myWebView = (WebView) rootView.findViewById(R.id.webView);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setPluginState(WebSettings.PluginState.OFF);
            webSettings.setAllowFileAccess(false);
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(new WebAppInterface(getActivity()), "gizalaugh");
            myWebView.setBackgroundColor(0x00000000);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            myWebView.setScrollContainer(false);

            myWebView.loadUrl("http://www.gizalaugh.tv/app/android/v1/index.php");
            myWebView.setWebViewClient(new XvGFormClient());

            loadingProgressBar=(ProgressBar) rootView.findViewById(R.id.progressBar);





            myWebView.setWebChromeClient(new WebChromeClient() {


                @Override

                public void onProgressChanged(WebView view, int newProgress) {

                    super.onProgressChanged(view, newProgress);

                    loadingProgressBar.setProgress(newProgress);
                    //loadingTitle.setProgress(newProgress);
                    // hide the progress bar if the loading is complete

                    if (newProgress == 100) {
                        loadingProgressBar.setVisibility(View.GONE);

                    } else {
                        loadingProgressBar.setVisibility(View.VISIBLE);

                    }

                }

            });

            myWebView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        WebView webView = (WebView) v;

                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                if (webView.canGoBack()) {
                                    webView.goBack();
                                    return true;
                                }
                                break;
                        }
                    }

                    return false;
                }
            });

        }

        return rootView;
    }


    private class XvGFormClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url) {

            if (!DetectConnection.checkInternetConnection(getActivity())) {
                Intent intent = new Intent(getActivity(), NoInternet.class);
                startActivity(intent);
                getActivity().finish();
            }
                try {
                    Boolean result;
                    if (url.contains("gizalaugh.tv")) {
                        //Allow the WebView in your application to do its thing
                        myWebView.loadUrl(url);
                        return true;
                    } else {
                        //Pass it to the system, doesn't match your domain
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        //Tell the WebView you took care of it.
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }


    }


}

