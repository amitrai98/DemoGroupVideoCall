package com.example.zenix.demogroupvideocall;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.PatternSyntaxException;

public abstract class BaseAcitvity extends AppCompatActivity {

    private FrameLayout activityContent = null;
    private Toolbar mToolbar = null;
    private TextView mTitleText, mSubtitleTxt;
    private int mLayoutId = 0;
    public static Toast toast;
    int mActionBarSize;
    protected PrefManager mPrefManager;
    protected Util mUtil;
    protected ImageView mBackBtn;
    private boolean doubleBackToExitPressedOnce;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_base_acitvity);
        if (toast == null) {
            toast = new Toast(this);
        }
        mPrefManager = PrefManager.newInstance(this);
        mUtil = new Util(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.tv_toolbar);
        mSubtitleTxt = (TextView) findViewById(R.id.tv_toolbar_subtitile);
        activityContent = (FrameLayout) this.findViewById(R.id.activity_content);
        mBackBtn = (ImageView) findViewById(R.id.tb_back);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // find the actionbarSize
        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        mActionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();


    }


    public void showBackButton() {
        mBackBtn.setVisibility(View.VISIBLE);
    }

    public void hideBackButton() {
        mBackBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * initialize Toolbar
     *
     * @param title        : title to set to Toolbar
     * @param isHomeEnable : check is Back Button Enable or not
     */
    public void initToolBar(String title, boolean isHomeEnable) {
        mToolbar.setVisibility(View.VISIBLE);
        mTitleText.setText(title);
        mTitleText.setTypeface(Typeface.DEFAULT_BOLD);
        setSupportActionBar(mToolbar);
        if (isHomeEnable) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);

        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);
        }


    }


    /**
     * tool bar for chat screen one to one
     *
     * @param title
     * @param isHomeEnable
     * @param isSubtitileShow
     */
    public void initToolBar(String title, boolean isHomeEnable, boolean isSubtitileShow) {
        mToolbar.setVisibility(View.VISIBLE);
        mTitleText.setText(title);
        mTitleText.setTypeface(Typeface.DEFAULT_BOLD);
        if (isSubtitileShow) {
            mSubtitleTxt.setVisibility(View.VISIBLE);
        } else {
            mSubtitleTxt.setVisibility(View.VISIBLE);
        }
        setSupportActionBar(mToolbar);
        if (isHomeEnable) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);

        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);
        }
    }


    /**
     * initialize Toolbar
     *
     * @param title                 : title to set to Toolbar
     * @param isHomeEnable          : check is Back Button Enable or not
     * @param customDrawable;custom back button
     */

    public void initToolBar(String title, boolean isHomeEnable, int customDrawable) {
        mToolbar.setVisibility(View.VISIBLE);
        mTitleText.setText(title);
        mTitleText.setTypeface(Typeface.DEFAULT_BOLD);
        setSupportActionBar(mToolbar);
        if (isHomeEnable) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(customDrawable);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);

        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setTitle(null);
        }

    }


    public void removeToolbar() {
        mToolbar.setVisibility(View.GONE);
    }

    protected FrameLayout getParentView() {
        return activityContent;
    }

    protected void setMyContentView(int layout) {
        getLayoutInflater().inflate(layout, getParentView());
    }

    /**
     * replace given fragment to layout
     *
     * @param fragment        fragment to replace
     * @param layoutToReplace layout which hold fragment/
     */
    public void replaceFragment(Fragment fragment, int layoutToReplace) {
        replaceFragment(fragment, layoutToReplace, true);
    }

    /**
     * initialize common methods and utilities
     */
    /**
     * initialize all views of activity under this method
     */
    protected abstract void initViews();


    /**
     * initialize manager classes and user program under this method
     */
    protected abstract void initManagers();


    /**
     * override onBackPressed to maintain Fragment Stack OnBackPress
     */
    @Override
    public void onBackPressed() {

        if (toast != null) {
            toast.cancel();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {

            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
//            finish();
            return;
        }

        super.onBackPressed();
    }

    public void setText(TextView view, String data) {
        if (view != null && data != null) {
            view.setText(data);
        }
    }

    public String getViewText(TextView view) {
        return view.getText().toString();
    }

    public Toolbar getToolBar() {
        if (mToolbar == null) {
            initToolBar("", true);
        }
        return mToolbar;
    }

    /**
     * logout from facebook if user any access token is stored in app.
     */
    public void logOutFb() {
//        AccessToken token = AccessToken.getCurrentAccessToken();
//        if (token != null) {
//            LoginManager manager = LoginManager.getInstance();
//            manager.logOut();
//        }
    }


    /**
     * @param fragment        fragment to replace
     * @param layoutToReplace layout to replace
     * @param storeInStack    boolean store in back stack
     */
    public void replaceFragment(Fragment fragment, int layoutToReplace, Boolean storeInStack) {

        try {
            View view = findViewById(layoutToReplace);
            if (mLayoutId == 0) {
                if (view != null) {
                    mLayoutId = layoutToReplace;
                }
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(mLayoutId, fragment);
            if (storeInStack) {
                transaction.addToBackStack(fragment.getClass().getCanonicalName());
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param fragment        fragment to replace
     * @param layoutToReplace layout to replace
     * @param storeInStack    boolean store in back stack
     * @param storeInStack    boolean add or replace
     */
    public void replaceFragment(Fragment fragment, int layoutToReplace, boolean storeInStack, boolean isAdd) {

        try {
            View view = findViewById(layoutToReplace);
            if (mLayoutId == 0) {
                if (view != null) {
                    mLayoutId = layoutToReplace;
                }
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (isAdd) {
//                transaction.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up, 0, R.anim.slide_in_up);
                transaction.add(mLayoutId, fragment);
            } else {
                transaction.replace(mLayoutId, fragment);
            }

            if (storeInStack) {
                transaction.addToBackStack(fragment.getClass().getCanonicalName());
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

//        switch (requestCode) {
//            case Constants.REQUEST_CHECK_SETTINGS:
//                switch (resultCode) {
//                    case Activity.RESULT_OK:
//                       /* mlocationData.setonLocationListener(null);
//                        mlocationData.disconnect();
//                        initiateLocation();*/
//                        break;
//                    case Activity.RESULT_CANCELED:
//
//                        break;
//                    default:
//                        break;
//                }
//                break;
//        }
    }

    /**
     * @param message
     * @param lenth   TSnackbar.LengthShort ,TSnackbar.LengthLong
     */
    public void showSnackbar(String title, String message, int lenth) {


//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.custom_toast,
//                (ViewGroup) findViewById(R.id.toast_layout_root));
//
//        TextView text = (TextView) layout.findViewById(R.id.text_error_subtitle);
//        text.setText(message);
//        TextView textTitle = (TextView) layout.findViewById(R.id.text_error_title);
//        text.setText(message);
//        textTitle.setText(title);
//        if(title.isEmpty()){
//            textTitle.setVisibility(View.GONE);
//        }else{
//            textTitle.setVisibility(View.VISIBLE);
//        }
        if (toast != null) {
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, mActionBarSize);
            toast.setDuration(Toast.LENGTH_SHORT);
//            toast.setView(layout);
            toast.show();
        }

    }

    public Toast getToast() {
        if (toast != null) {
            return toast;
        }
        return null;
    }


    /**
     * @param serviceClass (name of service to check)
     * @return is service running or not
     */
    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param content    content to be splitted
     * @param expression expression like @,# etc
     * @return
     * @throws NullPointerException
     * @throws PatternSyntaxException
     */
    public String splitString(String content, String expression) throws NullPointerException, PatternSyntaxException {

        String[] arr = content.split(expression);
        return arr[0];
    }
}
