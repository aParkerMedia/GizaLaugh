package uk.aparkermedia.gizalaugh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoPlayer extends Activity implements MediaPlayer.OnCompletionListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (!DetectConnection.checkInternetConnection(this)) {
            Intent intent = new Intent(this, NoInternet.class);
            startActivity(intent);
            finish();
        } else {

        String url = getIntent().getExtras().getString("url");

            videoView.setBackgroundColor(0x00000000);
            videoView.setMediaController(new MediaController(VideoPlayer.this));
            videoView.setOnCompletionListener(VideoPlayer.this);
            videoView.setVideoURI(Uri.parse(url));
            videoView.start();

            progressBar.setVisibility(View.VISIBLE);


            if (getIntent().getExtras() != null) {

                if (url != null) {
                    videoView.setOnPreparedListener(new OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            mp.start();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }
            }

            if (url == null) {
                throw new IllegalArgumentException("Must set url extra paremeter in intent.");
            }


        }



    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!DetectConnection.checkInternetConnection(this)) {
            Intent intent = new Intent(this, NoInternet.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onCompletion(MediaPlayer v) {
        finish();
    }

    //Convenience method to show a video
    public static void showRemoteVideo(Context ctx, String url) {
        Intent i = new Intent(ctx, VideoPlayer.class);

        i.putExtra("url", url);
        ctx.startActivity(i);
    }


}