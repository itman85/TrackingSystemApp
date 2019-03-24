package phannguyen.com.trackingsystem.ui.parse;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseInstallation;

import java.util.HashMap;
import java.util.Map;

import phannguyen.com.trackingsystem.Utils;

import static phannguyen.com.trackingsystem.ui.parse.ParseConfig.APP_KEY;
import static phannguyen.com.trackingsystem.ui.parse.ParseConfig.CLIENT_KEY;
import static phannguyen.com.trackingsystem.ui.parse.ParseConfig.PARSE_HOST_INIT;

public class ParseHelper {
    public static void ParseInit(final Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId(APP_KEY)
                .clientKey(CLIENT_KEY)
                .server(PARSE_HOST_INIT) // The trailing slash is important.
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }


    public static void submitLocationToServer(String userId, Location location) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userid", userId);
            params.put("lng",location.getLongitude());
            params.put("lat",location.getLatitude());
            params.put("time",System.currentTimeMillis());
            ParseCloud.callFunctionInBackground("submit_device_location", params,
                    (object, e) -> {
                        if(e==null) {
                            Log.i( "ParseHelper", "Send post to function  submit_device_location response OK.");

                        }else{
                            Log.i( "ParseHelper", "Send post to function  submit_device_location response FAIL.");
                        }
                    });
        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.e("ParseHelper", "Exception " + stackTrace);
            //throw new Exception(e);
        }
    }

    public static void submitUserStatusToServer(String userId, int status) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userid", userId);
            params.put("status",status);
            params.put("id",Utils.userObjectIDs.get(userId));
            params.put("time",System.currentTimeMillis());
            ParseCloud.callFunctionInBackground("submit_user_status", params,
                    (object, e) -> {
                        if(e==null) {
                            Log.i( "ParseHelper", "Send post to function submit_user_status response OK.");

                        }else{
                            Log.i( "ParseHelper", "Send post to function  submit_user_status response FAIL.");
                        }
                    });
        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.e("ParseHelper", "Exception " + stackTrace);
            //throw new Exception(e);
        }
    }
}
