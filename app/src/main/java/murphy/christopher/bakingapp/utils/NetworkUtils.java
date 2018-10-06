package murphy.christopher.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    //This will check to see if we have a connection to
    //the internet
    public boolean hasInternetService(Context con){
        boolean hasService = false;

        ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isAvailable() == true
                && activeNetwork.isConnected()){
            hasService = true;
        }

        return hasService;
    }
}
