package com.ahujasahab.androidassignment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {


    private ImageView myimageview;

    public ImageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_image, container, false);
        myimageview=(ImageView)view.findViewById(R.id.fullimageiv);
        Bundle bundle=getArguments();
        String url=bundle.getString("imageurl");
        if(url !=null)
        {
            Picasso.with(getContext()).load(url).resize(400, 400).into(myimageview);
        }
        return view;
    }


}
