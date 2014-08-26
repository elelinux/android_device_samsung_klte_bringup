/*
 * Copyright (C) 2014 Arnav Gupta, AOKP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cyanogenmod.settings.device.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.util.Log;

import com.cyanogenmod.settings.device.R;
import com.cyanogenmod.settings.device.Utils;

/**
 * Created by championswimmer on 10/2/14.
 */
public class KnockOnPreference extends CheckBoxPreference {

    public static String TAG = "KnockOnPreference";

    public static String SYSFS_PATH = null;
    public static String ENABLED_VALUE;
    public static String DISABLED_VALUE;

    private Context CONTEXT;

    public KnockOnPreference(final Context context, final AttributeSet attrst) {
        super(context, attrst);
        CONTEXT = context;
        SYSFS_PATH = context.getString(R.string.doubletapwake_sysfs_file);
        ENABLED_VALUE = context.getString(R.string.doubletapwake_enabled_value);
        DISABLED_VALUE = context.getString(R.string.doubletapwake_disabled_value);
    }

    @Override
    protected void onBindView(final View v) {
        super.onBindView(v);
    }

    @Override
    protected void onClick() {
        String val = getValueFromState(!isChecked());
        setChecked(!isChecked());
        //Log.d(TAG, SYSFS_PATH + val);
        Utils.writeValue(SYSFS_PATH, val);
    }

    public static void restore(Context context) {
        SYSFS_PATH = context.getString(R.string.doubletapwake_sysfs_file);
        DISABLED_VALUE = context.getString(R.string.doubletapwake_disabled_value);
        ENABLED_VALUE = context.getString(R.string.doubletapwake_enabled_value);
        if (!Utils.fileExists(SYSFS_PATH)) {
            return;
        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String value = (settings.getBoolean("doubletap_gesture", false) ? ENABLED_VALUE : DISABLED_VALUE);
        Utils.writeValue(SYSFS_PATH, value);
    }

    public String getValueFromState(Boolean state) {
        if (state) {
            return ENABLED_VALUE;
        } else {
            return DISABLED_VALUE;
        }
    }

    public Boolean checkSupport() {
        SYSFS_PATH = getContext().getString(R.string.doubletapwake_sysfs_file);
        Boolean fileExists = Utils.fileExists(SYSFS_PATH);
        if ((fileExists)) {
            return true;
        } else {
            Log.w(TAG, "File exists : " + SYSFS_PATH + " : " + fileExists);
            setEnabled(false);
            return false;
        }
    }

}
