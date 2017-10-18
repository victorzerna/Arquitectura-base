package com.cosmo.arquitecturamvpbase.views.adapter;

import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.views.fragments.ContactFragment;
import com.cosmo.arquitecturamvpbase.views.fragments.ProductFragment;

/**
 * Created by victorhugosernasuarez on 14/10/17.
 */

public class DashBoardTabsAdapter extends FragmentStatePagerAdapter {



    public DashBoardTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new ProductFragment();
            case 1:

                return new ContactFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;//Debe ser el numero de tabs a manejar
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return Constants.TITLE_PRODUCT;
            case 1:
                return Constants.TITLE_CONTACT;
            default:
                return Constants.EMPTY;
        }
    }
}
