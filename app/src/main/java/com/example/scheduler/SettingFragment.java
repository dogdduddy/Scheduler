package com.example.scheduler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

public class SettingFragment extends PreferenceFragment {

    SharedPreferences prefs;

    ListPreference soundPrefernence;
    ListPreference letterstylePrefernence;
    ListPreference lettersizePrefernence;
    EditTextPreference namePreference;

    // 이미 저장된 환경설정 내용을 가져와, 그 내용들을 환경설정의 summary부분에 세팅
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting_preference);
        letterstylePrefernence = (ListPreference) findPreference("letter_style_change");
        lettersizePrefernence = (ListPreference) findPreference("letter_size_change");
        namePreference = (EditTextPreference) findPreference("name");
        soundPrefernence = (ListPreference) findPreference("sound_list");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!prefs.getString("sound_list", "").equals("")) ;
        {
            soundPrefernence.setSummary(prefs.getString("sound_list", ""));
        }
        if (!prefs.getString("letter_style_change", "").equals("")) ;
        {
            letterstylePrefernence.setSummary(prefs.getString("letter_style_change", ""));
        }
        if (!prefs.getString("letter_size_change", "").equals("")) ;
        {
            lettersizePrefernence.setSummary(prefs.getString("letter_size_change", ""));
        }
        if (!prefs.getString("name", "").equals("")) ;
        {
            namePreference.setSummary(prefs.getString("name", ""));
        }

        prefs.registerOnSharedPreferenceChangeListener(preListener);
    }

    //봉뚜 사용자가 환경설정 하는 순간 이벤트 처리
    SharedPreferences.OnSharedPreferenceChangeListener preListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("sound_list")) {
                soundPrefernence.setSummary(prefs.getString("sound_list", ""));
            }
            if (key.equals("letter_style_change")) {
               letterstylePrefernence.setSummary(prefs.getString("letter_style_change", ""));
            }
            if (key.equals("letter_size_change")) {
                lettersizePrefernence.setSummary(prefs.getString("letter_size_change", ""));
            }
            if (key.equals("name")) {
                namePreference.setSummary(prefs.getString("name", ""));
            }
        }
    };
}