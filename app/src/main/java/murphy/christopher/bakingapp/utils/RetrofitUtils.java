package murphy.christopher.bakingapp.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Retrofit retrofitInstance;
    static {
        setRetrofitInstance();
    }

    private static void setRetrofitInstance(){
        if (retrofitInstance == null) {
            retrofitInstance = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }
    public static Retrofit getRetrofitInstance(){
        return retrofitInstance;
    }
}
