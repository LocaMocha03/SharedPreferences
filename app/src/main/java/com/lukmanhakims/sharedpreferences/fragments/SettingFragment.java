package com.lukmanhakims.sharedpreferences.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.lukmanhakims.sharedpreferences.R;

public class SettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences (Bundle bundle , String s ) {
        addPreferencesFromResource( R.xml.preferences);
    }
}
