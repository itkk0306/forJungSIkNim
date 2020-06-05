package com.example.petcarelog;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.realm.Realm;

import static com.example.petcarelog.R.drawable.bone;
import static com.example.petcarelog.R.drawable.heart;

public class FormRecyclerAdapter extends RecyclerView.Adapter<FormRecyclerAdapter.FormViewHolder> {
    private ArrayList<FormData> mFormData = new ArrayList<>();
    private Realm realm;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FormRecyclerAdapter(ArrayList<FormData> myFormData) {
        mFormData = myFormData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FormViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_form, parent, false);

        /////////////////////////////////////////////////////////////



        FormViewHolder vh = new FormViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final FormViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        FormData data = mFormData.get(position);

        holder.mFormEdit01.setText(data.getDignosis()); //내일!
        holder.mFormEdit02.setText(data.getSymptom());
        holder.mFormEdit03.setText(data.getNote());


        if(data.getType() == 1) {
            holder.mImageView.setImageResource(R.drawable.heart);


            holder.mFormText04.setVisibility(View.INVISIBLE);
            holder.mFormText05.setVisibility(View.GONE);

            holder.mFormEdit04.setVisibility(View.INVISIBLE);
            holder.mFormEdit05.setVisibility(View.GONE);

        }
        if (data.getType() == 2){
            holder.mImageView.setImageResource(R.drawable.eye);

            holder.mFormText04.setVisibility(View.INVISIBLE);
            holder.mFormText05.setVisibility(View.GONE);

            holder.mFormEdit04.setVisibility(View.INVISIBLE);
            holder.mFormEdit05.setVisibility(View.GONE);
        }

        if (data.getType() == 3){
            holder.mImageView.setImageResource(R.drawable.bone);

            holder.mFormText04.setVisibility(View.INVISIBLE);
            holder.mFormText05.setVisibility(View.GONE);

            holder.mFormEdit04.setVisibility(View.INVISIBLE);
            holder.mFormEdit05.setVisibility(View.GONE);
        }

        if (data.getType() == 4){
            holder.mFormText01.setText("ALKP");
            holder.mFormText02.setText("ALP");
            holder.mFormText03.setText("BUN");
            holder.mFormText04.setText("Creatine");
            holder.mFormText05.setText("메모");

            holder.mFormEdit04.setVisibility(View.VISIBLE);
            holder.mFormEdit05.setVisibility(View.VISIBLE);
            holder.mImageView.setImageResource(R.drawable.blood);

            holder.mFormEdit01.setText(data.getAklp()); //내일!
            holder.mFormEdit02.setText(data.getAlp());
            holder.mFormEdit03.setText(data.getBun());
            holder.mFormEdit04.setText(data.getCreatine());
            holder.mFormEdit05.setText(data.getNote());
        }




        ///////////////////////////////////////////////////////////////////////////////

        holder.mFormEdit01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //text가 바뀔 때 마다 호출됨

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //사용자가 텍스트를 입력하고 나서 멈춤. 변경이 모두 일어나고 나면, 그러면 호출!
                mFormData.get(position).setDignosis(editable.toString());
                mFormData.get(position).setSymptom(editable.toString());
                mFormData.get(position).setNote(editable.toString());
            }
        });

    }



    // Return the size of your dataset (invoked by the layout manager)

    //
    @Override
   public int getItemCount() {
        return mFormData.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class FormViewHolder extends RecyclerView.ViewHolder {

        EditText mFormEdit01;
        EditText mFormEdit02;
        EditText mFormEdit03;
        EditText mFormEdit04;
        EditText mFormEdit05;

        TextView mFormText01;
        TextView mFormText02;
        TextView mFormText03;
        TextView mFormText04;
        TextView mFormText05;


        ImageView mImageView;

        public FormViewHolder(ConstraintLayout parent) {
            super(parent);
            mFormEdit01 = parent.findViewById(R.id.item_edit_01);
            mFormEdit02 = parent.findViewById(R.id.item_edit_02);
            mFormEdit03 = parent.findViewById(R.id.item_edit_03);

            mImageView = parent.findViewById(R.id.img1);

//////////////////////////////////////////////////////////////////////////////////

            mFormText01 = parent.findViewById(R.id.item_view_01);
            mFormText02 = parent.findViewById(R.id.item_view_02);
            mFormText03 = parent.findViewById(R.id.item_view_03);
            mFormText04 = parent.findViewById(R.id.item_view_04);
            mFormText05 = parent.findViewById(R.id.item_view_05);

            mFormEdit04 = parent.findViewById(R.id.item_edit_04);
            mFormEdit05 = parent.findViewById(R.id.item_edit_05);

        }
    }
}
    