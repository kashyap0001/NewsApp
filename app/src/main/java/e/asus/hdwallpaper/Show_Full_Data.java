package e.asus.hdwallpaper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Show_Full_Data extends AppCompatActivity  {

    TextView v1;
    ImageView views;
    String url;
    Bitmap bitmap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__full__data);
        views = findViewById(R.id.imageview);

        Intent i = getIntent();
        url = i.getStringExtra("ImageUrl");

        ShowImage showimg = new ShowImage(url);
        showimg.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbutton,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        try {
            manager.setBitmap(bitmap);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext()).
                    setContentText("Wallpaperset successfull")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("notification recieved");

            Intent i = new Intent(this,MainActivity.class);
            PendingIntent intn = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationManager managers = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            managers.notify(0,notification.build());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }

    class ShowImage extends AsyncTask<Void,String,Void>{
        String url;
//        ProgressDialog pd = new ProgressDialog(getApplicationContext());
        ShowImage(String url){
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pd.setMessage("Please Wait");
//            pd.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                bitmap = Picasso.with(getApplicationContext()).load(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            publishProgress(url);
            return null;
        }

        @Override
        protected void onProgressUpdate(final String... values) {
            super.onProgressUpdate(values);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Picasso.with(views.getContext()).load(values[0]).into(views);
                }
            });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            pd.dismiss();
        }
    }

}
