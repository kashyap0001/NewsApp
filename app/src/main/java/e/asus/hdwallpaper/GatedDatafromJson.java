package e.asus.hdwallpaper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 21-May-19.
 */

public class GatedDatafromJson extends AsyncTask<Void, ImageData, Void> {
    RecyclerView setdata;
    Context context;
//    ProgressDialog pd;

    public GatedDatafromJson(RecyclerView setdata, Context context) {
        this.setdata = setdata;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pd = new ProgressDialog(context);
//        pd.setMessage("Please Wait");
//        pd.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        String key = "cff54103f7eb4b58ab090466e1aaa767";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ImageDataInterface.url1).addConverterFactory(GsonConverterFactory.create()).build();

        ImageDataInterface imgdataintrface = retrofit.create(ImageDataInterface.class);

        Call<ImageData> imgdata = imgdataintrface.getData("in",key);

        imgdata.enqueue(new Callback<ImageData>() {
            @Override
            public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                ImageData data = response.body();
                publishProgress(data);

                Log.w("get", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<ImageData> call, Throwable t) {
                Log.w("get", t.getMessage());

            }
        });


        return null;
    }

    @Override
    protected void onProgressUpdate(final ImageData... values) {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
                setdata.setLayoutManager(manager);
                setdata.setAdapter(new SetDataToRecyclerview(values[0].getArticles()));
            }
        });

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        pd.dismiss();
    }
}
