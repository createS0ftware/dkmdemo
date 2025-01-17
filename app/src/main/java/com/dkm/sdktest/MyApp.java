/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.dkm.sdktest;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.dkm.dkmdevice.DkmDevice;
import com.dkm.dkmdevice.RegCallBack;
import com.hjq.toast.ToastUtils;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
    }
}
