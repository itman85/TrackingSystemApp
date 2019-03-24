/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package phannguyen.com.trackingsystem;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import phannguyen.com.trackingsystem.ui.MainActivity;

public class Utils {

    public static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";

    public static final Map<String, String> userCredentails = new HashMap<String, String>()
    {
        {
            put("nv1", "Nguyễn Văn A");
            put("nv2", "Nguyễn Văn B");
            put("nv3", "Nguyễn Văn C");

        };
    };

    public static final Map<String, String> userObjectIDs = new HashMap<String, String>()
    {
        {
            put("nv1", "hVLplP9dB5");
            put("nv2", "cWhWSNxQ8n");
            put("nv3", "OcyH2WZOSu");

        };
    };

    /**
     * Returns true if requesting location updates, otherwise returns false.
     *
     * @param context The {@link Context}.
     */
    public static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     * @param requestingLocationUpdates The location updates state.
     */
    public static void setRequestingLocationUpdates(Context context, boolean requestingLocationUpdates) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }

    /**
     * Returns the {@code location} object as a human readable string.
     * @param location  The {@link Location}.
     */
    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static String getLocationTitle(Context context) {
        return context.getString(R.string.location_updated,
                DateFormat.getDateTimeInstance().format(new Date()));
    }

    public static String getLoginUser(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString("loginUser", "");
    }


    public static void setLoginUser(Context context, String userId) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("loginUser", userId)
                .apply();
    }

    public static void logoutAction(Context context){
        setLoginUser(context,"");
        Intent intent = new Intent(context, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//destroy all current activities, create new task for login activity
        context.startActivity(intent);
    }

    public static String getLoginUserFullName(Context context){
        return userCredentails.get(getLoginUser(context));
    }


}
