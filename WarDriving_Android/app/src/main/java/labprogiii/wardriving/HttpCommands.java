package labprogiii.wardriving;

import com.loopj.android.http.*;

/**
 * Created by USER on 17/02/2016.
 */
public class HttpCommands {
    private static final String PostURL = "http://172.16.38.24/WarProject/post";
    //private static final String GetURL = "http://172.16.34.122/android_connect/read_DB.php";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(PostURL, params, responseHandler);
    }

    /*public static void get(RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(GetURL, params, responseHandler);
    }*/
}