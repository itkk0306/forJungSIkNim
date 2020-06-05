package com.example.petcarelog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.DateTime;

import java.text.Normalizer;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class FormFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private LinearLayout heartLayout, eyeLayout, boneLayout, bloodLayout;

    //adapter의 역할은
    private FormRecyclerAdapter mAdapter;

    private ArrayList<FormData> mFormData = new ArrayList<>();

    TextView mFormComplete;
    FloatingActionButton mFabMain,mFabSub1, mFabSub2, mFabSub3, mFabSub4;

    EditText mEditText1,mEditText2,mEditText3;
    RecyclerView mRecyclerView;
    View mView;
    ImageView mImageView;

    FormData tempFormData_Heart = new FormData(DateTime.now().toString(), "","","",Constants.TYPE_HEART);
    FormData tempFormData_eye = new FormData(DateTime.now().toString(), "" ,"","",Constants.TYPE_EYE);
    FormData tempFormData_bone = new FormData(DateTime.now().toString(), "","","",Constants.TYPE_BONE);
    FormData tempFormData_blood = new FormData(DateTime.now().toString(),"","","","",Constants.TYPE_BLOOD);

    //////////////////////////////////////////////////////

    private Context mContext;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;



    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_form, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO : 뷰와 관련된 코드 초기화
        mImageView = mView.findViewById(R.id.img1);

        mFormComplete = mView.findViewById(R.id.form_complete);

        mEditText1 =mView.findViewById(R.id.item_edit_01);
        mEditText2 =mView.findViewById(R.id.item_edit_02);
        mEditText3 =mView.findViewById(R.id.item_edit_03);

        mFabMain = mView.findViewById(R.id.form_fab_main);
        mFabSub1 = mView.findViewById(R.id.form_fab_sub1);
        mFabSub2 = mView.findViewById(R.id.form_fab_sub2);
        mFabSub3 = mView.findViewById(R.id.form_fab_sub3);
        mFabSub4 = mView.findViewById(R.id.form_fab_sub4);

        mContext = getActivity();
     //   ViewCompat.animate(mFabMain).rotation(45.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();

        heartLayout = mView.findViewById(R.id.heartLayout);
        eyeLayout = mView.findViewById(R.id.eyeLayout);
        boneLayout= mView.findViewById(R.id.boneLayout);
        bloodLayout = mView.findViewById(R.id.bloodLayout);

        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);

        mFabSub1.startAnimation(fab_close);
        mFabSub2.startAnimation(fab_close);
        mFabSub3.startAnimation(fab_close);
        mFabSub4.startAnimation(fab_close);

        heartLayout.startAnimation(fab_close);
        eyeLayout.startAnimation(fab_close);
        boneLayout.startAnimation(fab_close);
        bloodLayout.startAnimation(fab_close);



        final Realm realm = Realm.getDefaultInstance();


        mFabMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            { toggleFab(); }
        });

        mFabSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFormData.add(tempFormData_Heart);
                mAdapter.notifyDataSetChanged();

                mFabSub1.setEnabled(false);

            }
        });




        mFabSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFormData.add(tempFormData_eye);
                mAdapter.notifyDataSetChanged();

                mFabSub2.setEnabled(false);

            }
        });

        mFabSub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFormData.add(tempFormData_bone);
                mAdapter.notifyDataSetChanged();

                mFabSub3.setEnabled(false);


            }
        });

        mFabSub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFormData.add(tempFormData_blood);
                mAdapter.notifyDataSetChanged();

                mFabSub4.setEnabled(false);

            }
        });

        mFormComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 트랜잭션에서 데이터 insert
                realm.beginTransaction();
                for(FormData data : mFormData){
                    realm.copyToRealm(data);
                }

       //         FormData test = mAdapter.getItemId();


                final RealmResults<FormData> heartData = realm.where(FormData.class).equalTo("type", Constants.TYPE_HEART).findAll();
                final RealmResults<FormData> eyeData = realm.where(FormData.class).equalTo("type", Constants.TYPE_EYE).findAll();
                final RealmResults<FormData> boneData = realm.where(FormData.class).equalTo("type", Constants.TYPE_BONE).findAll();
                final RealmResults<FormData> bloodData = realm.where(FormData.class).equalTo("type", Constants.TYPE_BLOOD).findAll();

                realm.commitTransaction();
                Timber.d("test " + heartData );
                Timber.d("test " + eyeData );
                Timber.d("test " + boneData );
                Timber.d("testBlood " + bloodData );






/*
                for(FormData formData : EyeData) {
                    mFormData.add(new FormData());
                    mAdapter = new FormRecyclerAdapter(mFormData);
                    mRecyclerView.setAdapter(mAdapter);
                }
*/




            }
        });

        mRecyclerView = mView.findViewById(R.id.form_view);

        mAdapter = new FormRecyclerAdapter(mFormData);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.addItemDecoration();



        /////////////////////////////////////////////////////////////////



    }

    private void toggleFab() {
        if (isFabOpen) {

            mFabMain.setImageResource(R.drawable.ic_baseline_add_24);
            mFabSub1.startAnimation(fab_close);
            mFabSub2.startAnimation(fab_close);
            mFabSub3.startAnimation(fab_close);
            mFabSub4.startAnimation(fab_close);

            heartLayout.startAnimation(fab_close);
            eyeLayout.startAnimation(fab_close);
            boneLayout.startAnimation(fab_close);
            bloodLayout.startAnimation(fab_close);

            mFabSub1.setClickable(false);
            mFabSub2.setClickable(false);
            mFabSub3.setClickable(false);
            mFabSub4.setClickable(false);

            isFabOpen = false;

        } else {

            mFabMain.setImageResource(R.drawable.ic_baseline_close_24);
            mFabSub1.startAnimation(fab_open);
            mFabSub2.startAnimation(fab_open);
            mFabSub3.startAnimation(fab_open);
            mFabSub4.startAnimation(fab_open);

            heartLayout.startAnimation(fab_open);
            eyeLayout.startAnimation(fab_open);
            boneLayout.startAnimation(fab_open);
            bloodLayout.startAnimation(fab_open);


            mFabSub1.setClickable(true);
            mFabSub2.setClickable(true);
            mFabSub3.setClickable(true);
            mFabSub4.setClickable(true);
            isFabOpen = true;

        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void onFloatingButtonClicked(FloatingActionButton mFloatingBtn){


    }



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
