package com.example.scheduler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

public class SettingFragment extends PreferenceFragment {

    SharedPreferences prefs;

    ListPreference soundPrefernence;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting_preference);
        soundPrefernence = (ListPreference) findPreference("sound_list");
        keywordSoundPreference = (ListPreference) findPreference("key_word_list");
        keywordScreen = (PreferenceScreen) findPreference("keyword_screen");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!prefs.getString("sound_list", "").equals("")) ;
        {
            soundPrefernence.setSummary(prefs.getString("sound_list", "카톡"));
        }
        /*
        if (!prefs.getString("keyword_sound_list", "").equals("")) ;
        {
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
        }
        */

        if (prefs.getBoolean("keyword", false)) {
            keywordScreen.setSummary("사용");
        }

        prefs.registerOnSharedPreferenceChangeListener(preListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener preListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("sound_list")) {
                soundPrefernence.setSummary(prefs.getString("sound_list", "카톡"));
            }
            if (key.equals("keyword_sound_list")) {
                keywordSoundPreference.setSummary((prefs.getString("keyword_sound_list", "카톡")));
            }

            if (key.equals("keyword")) {

                if (prefs.getBoolean("keyword", false)) {
                    keywordScreen.setSummary("사용");
                } else {
                    keywordScreen.setSummary("사용안함");
                }
                ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
            }
        }
    };
}