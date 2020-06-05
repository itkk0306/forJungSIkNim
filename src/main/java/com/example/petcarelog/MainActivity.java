package com.example.petcarelog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.DateTime;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FormFragment formFragment = new FormFragment();

        fragmentTransaction.replace(R.id.fragment_container, formFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

/*
        // this 스레드에 대한 Realm 인스턴스 생성
        Realm realm = Realm.getDefaultInstance();

        FormData formData = new FormData(DateTime.now().toString("yyyyMMdd HH:mm:ss"),"3", "아아","주의사항");

        // 트랜잭션에서 데이터 insert
        realm.beginTransaction();
        // 미리 만들어진 객체를 사용
        final FormData mformData = realm.copyToRealm(formData);
        realm.commitTransaction();
        // 찾아오기
        final RealmResults<FormData> puppies = realm.where(FormData.class).findAll();

        Timber.d("test " + puppies );

*/

        }



    }


