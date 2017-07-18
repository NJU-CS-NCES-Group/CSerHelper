package com.example.cserhelper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyHomeworkFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public MyHomeworkFragment() {
        // Required empty public constructor
    }

    public static MyHomeworkFragment newInstance(String param1) {
        MyHomeworkFragment fragment = new MyHomeworkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_homework, container, false);
        Button button=(Button)view.findViewById(R.id.personalButton);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.personalButton:
            {
                PersonalCenterActivity pca=(PersonalCenterActivity)getActivity();
                FragmentManager fm=pca.getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment fragment=pca.getFragments().get(4);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commit();
            }
        }
    }
}
