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

package com.cyanogenmod.settings.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cyanogenmod.settings.device.prefs.KnockOnPreference;
import com.cyanogenmod.settings.device.prefs.CameraPreference;
import com.cyanogenmod.settings.device.prefs.MusicPreference;
import com.cyanogenmod.settings.device.prefs.TorchPreference;
import com.cyanogenmod.settings.device.prefs.FastChargePreference;
import com.cyanogenmod.settings.device.prefs.VibratorTuningPreference;

public class Startup extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        VibratorTuningPreference.restore(context);
        KnockOnPreference.restore(context);
        CameraPreference.restore(context);
        MusicPreference.restore(context);
        TorchPreference.restore(context);
        FastChargePreference.restore(context);
    }
}
