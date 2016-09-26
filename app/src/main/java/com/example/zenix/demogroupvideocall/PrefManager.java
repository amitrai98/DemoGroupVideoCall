package com.example.zenix.demogroupvideocall;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by amandeepsingh on 26/5/16.
 */
public class PrefManager {

    private static final String PRODUCTS_IN_CART = "product_in_cart";
    private static final String CLIENT_TOKEN = "client_token";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String EMAIL = "email";
    private static final String USER_TYPE = "user_type";
    private static final String USER_NAME = "user_name";
    private static final String USER_ID = "user_id";
    private static final String IS_USER_LOGIN = "is_user_login";
    private SharedPreferences mPref;
    private Context mContext;
    private final static String PREF_NAME = "myPref";
    private SharedPreferences.Editor mEditor;
    private static PrefManager sPrefManager;
    private static final String KEY_GCM = "gcm_token";

    public PrefManager(Context context) {
        mContext = context;
        mPref = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static PrefManager newInstance(Context context) {
        if (sPrefManager == null)
            sPrefManager = new PrefManager(context);
        return sPrefManager;
    }

    public void setGcmToken(String token) {
        mEditor.putString(KEY_GCM, token);
        mEditor.commit();
    }

    public String getGCMToken() {
        return mPref.getString(KEY_GCM, null);

    }

//    @SerializedName("userId")
//    @Expose
//    private String userId;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("userType")
//    @Expose
//    private String userType;
//    @SerializedName("emailId")
//    @Expose
//    private String emailId;

//    public void setUserLoginTrue(User userDetail) {
//
//        mEditor.putString(PRODUCTS_IN_CART, userDetail.getTotalProductsInCart());
//        mEditor.putString(CLIENT_TOKEN, userDetail.getClientToken());
//        mEditor.putString(ACCESS_TOKEN, userDetail.getAccessToken());
//        mEditor.putString(EMAIL, userDetail.getEmailId());
//        mEditor.putString(USER_TYPE, userDetail.getUserType());
//        mEditor.putString(USER_NAME, userDetail.getName());
//        mEditor.putString(USER_ID, userDetail.getUserId());
//        mEditor.putBoolean(IS_USER_LOGIN, true);
//        mEditor.commit();
//    }

    public void logoutUser() {
        mEditor.clear().commit();
    }

    public String getTotalItemInCart()
    {
        return mPref.getString(PRODUCTS_IN_CART,"0");
    }

    public boolean isUserLogin()
    {
        return mPref.getBoolean(IS_USER_LOGIN,false);
    }

    public String getAccessToken()
    {
        return mPref.getString(ACCESS_TOKEN,"");
    }

    public String getDriverId()
    {
        return mPref.getString(USER_ID,"");
    }
}
