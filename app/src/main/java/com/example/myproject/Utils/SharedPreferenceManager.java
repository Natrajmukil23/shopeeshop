package com.example.myproject.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    //the constants
    private static final String SHARED_PREF_NAME = "Ala_shared";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_FIRST_NAME = "firstname";
    private static final String KEY_LAST_NAME = "lastname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_NAME = "keygender";
    private static final String KEY_PROFILE = "KEY_PROFILE";
    private static final String KEY_ROLE_ID = "keyrole";
    private static final String KEY_DESIGNATION = "keydesignation";
    private static final String KEY_PHONE = "keylastlogged";
    private static final String KEY_INCHARGE_PHONE = "KEY_INCHARGE_PHONE";
    private static final String KEY_COMPANY_NAME = "keyname";
    private static final String KEY_COMPANY_ID = "keycompanyid";
    private static final String KEY_OFICE_ID = "keyoffice_id";
    private static final String KEY_EMP_ID = "emp_id";
    private static final String KEY_INCHARGE_NAME = "incharge_name";
    private static final String KEY_INCHARGE_ID = "incharge_id";
    private static final String KEY_ID = "keyid";
    private static final String KEY_LOGIN_URL = "KEY_LOGIN_URL";

    private static SharedPreferenceManager mInstance;
    private static Context mCtx;

    private SharedPreferenceManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(myproject_user user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getuser_id());
        editor.putString(KEY_FIRST_NAME, user.getfirst_name());
        editor.putString(KEY_LAST_NAME, user.getlast_name());
        editor.putString(KEY_NAME, user.getusername());
        editor.putString(KEY_EMAIL, user.getemail());
        editor.putString(KEY_ROLE_ID, user.getuser_role_id());
        editor.putString(KEY_DESIGNATION, user.getdesignation());
        editor.putString(KEY_PHONE, user.getphone());
        editor.putString(KEY_COMPANY_NAME, user.getcompany_name());
        editor.putString(KEY_COMPANY_ID, user.getcompany_id());
        editor.putString(KEY_OFICE_ID, user.getoffice_id());
        editor.putString(KEY_LOGIN_URL, user.getlogin_url());
        editor.putString(KEY_PROFILE, user.getprofile_img());
        editor.putString(KEY_INCHARGE_NAME, user.getincharge_name());
        editor.putString(KEY_INCHARGE_ID, user.getincharge_id());
        editor.putString(KEY_INCHARGE_PHONE, user.getincharge_phone_number());
        editor.putString(KEY_EMP_ID, user.getemp_id());
        editor.apply();


    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public myproject_user getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new myproject_user(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_FIRST_NAME, null),
                sharedPreferences.getString(KEY_LAST_NAME, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_ROLE_ID, null),
                sharedPreferences.getString(KEY_DESIGNATION, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_COMPANY_NAME, null),
                sharedPreferences.getString(KEY_COMPANY_ID, null),
                sharedPreferences.getString(KEY_OFICE_ID, null),
                sharedPreferences.getString(KEY_LOGIN_URL, null),
                sharedPreferences.getString(KEY_PROFILE, null),
                sharedPreferences.getString(KEY_INCHARGE_NAME, null),
                sharedPreferences.getString(KEY_INCHARGE_ID, null),
                sharedPreferences.getString(KEY_INCHARGE_PHONE, null),
                sharedPreferences.getString(KEY_EMP_ID, null)



        );
    }


    public static void deleteAllSharePrefs(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }


    //this method will logout the user
//    public void logout() {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, Survey_LoginActivity.class));
//    }
}
