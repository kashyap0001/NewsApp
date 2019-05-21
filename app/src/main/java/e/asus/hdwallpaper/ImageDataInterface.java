package e.asus.hdwallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ASUS on 21-May-19.
 */

public interface ImageDataInterface {
    String url = "https://pixabay.com/";

    String url1 = "https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<ImageData> getData(@Query("country") String country,@Query("apiKey") String apiKey);


}
