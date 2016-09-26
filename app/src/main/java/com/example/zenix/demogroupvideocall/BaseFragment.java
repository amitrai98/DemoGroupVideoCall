package com.example.zenix.demogroupvideocall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;



/**
 * Created by amandeep on 13/10/15.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseAcitvity mActivity;
    protected Util mUtil;
    protected PrefManager mPrefManager;
    protected com.example.zenix.demogroupvideocall.DateUtils mDateUtils;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseAcitvity) {
            mActivity = (BaseAcitvity) context;
            mUtil= new Util(context);
            mPrefManager = PrefManager.newInstance(context);
            mDateUtils= DateUtils.getInstance();
//            mDateUtils = DateUtils.getInstance();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mUtil= new Util(activity);
        mActivity = (BaseAcitvity) activity;
        mPrefManager = PrefManager.newInstance(activity);
    }

    /**
     * Replace current view to given fragment from fragment
     *
     * @param fragment
     */
    protected void replaceFragment(Fragment fragment) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, 10);
    }


    protected void replaceFragment(Fragment fragment, Boolean storeInStack) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, 10, storeInStack);
    }

    protected void replaceFragment(Fragment fragment, boolean storeInStack, boolean isAdd) {
        if(mActivity.getToast()!=null){
            mActivity.getToast().cancel();
        }
        mActivity.replaceFragment(fragment, 10, storeInStack, isAdd);
    }

    /**
     * initialize all views of activity under this method
     */
    protected abstract void initViews(View view);

    @Override
    public void onStop() {
        super.onStop();

    }
}
