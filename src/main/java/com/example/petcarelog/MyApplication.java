/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.petcarelog;

import android.app.Application;

import org.joda.time.DateTime;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import timber.log.Timber;

public class MyApplication extends Application {

    //어플리케이션 전체를 지칭하는 Application, 딱 한번만 실행된다.
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded() //migration 필요하면 realm 지우고 새로 만든다.
                .build();

        Realm.setDefaultConfiguration(config);




    }
}
