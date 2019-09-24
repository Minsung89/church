package com.ydn.church.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ydn.church.Adapter.MalseumRvAdapter;
import com.ydn.church.Entity.MalsseumContent;
import com.ydn.church.R;

import java.util.List;


public class MalsseumContentFragment extends Fragment {

    RecyclerView rv;
    List<MalsseumContent> mcs;
    MalseumRvAdapter malseumRvAdapter;
    ImageView malsseumContentEnlargement, malsseumContentReduction;

    public MalsseumContentFragment(){}


    @SuppressLint("ValidFragment")
    public MalsseumContentFragment(List<MalsseumContent> mcs, ImageView malsseumContentEnlargement, ImageView malsseumContentReduction){
        this.mcs = mcs;
        this.malsseumContentReduction = malsseumContentReduction;
        this.malsseumContentEnlargement = malsseumContentEnlargement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.malsseum_content_fragment, container, false);

        rv = (RecyclerView) rootview.findViewById(R.id.malsseum_content_fm_rv);
        rv.setHasFixedSize(true);



        if(mcs != null) {
            malseumRvAdapter = new MalseumRvAdapter(mcs, getActivity(), malsseumContentEnlargement, malsseumContentReduction);

            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv.setAdapter(malseumRvAdapter);

        }

        return rootview;
    }
}
