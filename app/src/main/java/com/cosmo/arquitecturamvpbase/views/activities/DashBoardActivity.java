package com.cosmo.arquitecturamvpbase.views.activities;

import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.DashBoardTabsAdapter;

public class DashBoardActivity extends BaseActivity {

    private TabLayout dashBoard_tlTabs;
    private ViewPager dashBoard_vpContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_dash_board);
        loadViews();
        loadTabsAdapter();
    }

    private void loadTabsAdapter() {
        DashBoardTabsAdapter dashBoardTabsAdapter = new DashBoardTabsAdapter(getSupportFragmentManager());
        dashBoard_vpContainer.setAdapter(dashBoardTabsAdapter);
        dashBoard_tlTabs.setupWithViewPager(dashBoard_vpContainer);
        dashBoard_tlTabs.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        dashBoard_tlTabs.setTabTextColors(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimaryDark)));

    }

    private void loadViews() {
        dashBoard_tlTabs = (TabLayout)findViewById(R.id.dash_BoardTabLayout);
        dashBoard_vpContainer = (ViewPager)findViewById(R.id.dash_BoarViewPager);
    }

}
