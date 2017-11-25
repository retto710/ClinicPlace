package com.example.anthony.clinicplace;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anthony.clinicplace.ClinicFragment.OnListFragmentInteractionListener;
import com.example.anthony.clinicplace.dummy.DummyContent;
import com.example.anthony.clinicplace.dummy.DummyContent.DummyItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>  {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyItemRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mAdressView.setText(mValues.get(position).details);
        holder.setOnclickListeners();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                }

             }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //context
        Context context;
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mAdressView;
        public final ImageView imageView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            context=view.getContext();
            mView = view;
            imageView=(ImageView) view.findViewById(R.id.image);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.nombre);
            mAdressView=(TextView)view.findViewById(R.id.sede);
        }
        void setOnclickListeners()
        {
            imageView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.image:{
                    Intent intent=new Intent(context,ClinicActivity .class);
                    intent.putExtra("Nombre",mContentView.getText().toString());
                    context.startActivity(intent);

                }
            }
        }
    }
}
