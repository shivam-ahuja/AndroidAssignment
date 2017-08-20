package com.ahujasahab.androidassignment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 19-08-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    MainActivity mContext;
    ArrayList<Worldpopulation> myarrayList;
    LayoutInflater lif;
    private static final String TAG = "MainActivity";
    private View view;
    private FragmentManager mFragmentManager;

    public MyAdapter(MainActivity mContext, ArrayList<Worldpopulation> myarrayList) {
        Log.d(TAG,"in myadapter");
        this.mContext = (MainActivity) mContext;
        this.myarrayList = myarrayList;
        lif=LayoutInflater.from(mContext);

    }



    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       view=lif.inflate(R.layout.itemview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder,  int position) {
holder.name.setText(myarrayList.get(position).getCountry());
        holder.population.setText(myarrayList.get(position).getPopulation());
        holder.rank.setText(String.valueOf(myarrayList.get(position).getRank()));
        Picasso.with(mContext).load(myarrayList.get(position).getFlag()).resize(100, 100).into(holder.flag);


    }

    @Override
    public int getItemCount() {
        return myarrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {

        private TextView name;
        private TextView rank;
        private TextView population;
        private ImageView flag;

        public MyViewHolder(View itemView) {
            super(itemView);
            population=(TextView)itemView.findViewById(R.id.mypopulation);
            flag = (ImageView) itemView.findViewById(R.id.myflag);
            rank=(TextView)itemView.findViewById(R.id.myrank);
            name=(TextView)itemView.findViewById(R.id.myname);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mFragmentManager=mContext.getFragmentManager();

                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    ImageFragment imageFragment=new ImageFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("imageurl",myarrayList.get(getAdapterPosition()).getFlag());
                    imageFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.myframelayout,imageFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });


        }
    }
}
