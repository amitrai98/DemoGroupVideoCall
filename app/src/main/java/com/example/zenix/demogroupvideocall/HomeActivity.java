package com.example.zenix.demogroupvideocall;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HomeActivity extends BaseAcitvity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyContentView(R.layout.activity_home);
    }

    @Override
    protected void initViews() {
        Fragment fragment = HomeFragment.newInstance("","");
        replaceFragment(fragment, R.id.dashboard_content);
    }

    @Override
    protected void initManagers() {

    }
}
