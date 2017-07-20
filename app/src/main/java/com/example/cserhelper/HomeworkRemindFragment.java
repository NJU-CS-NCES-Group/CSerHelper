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

public class HomeworkRemindFragment extends Fragment implements View.OnClickListener
{


    public HomeworkRemindFragment() {
    }
    public static HomeworkRemindFragment newInstance(String param1) {
        HomeworkRemindFragment fragment = new HomeworkRemindFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homework_remind, container, false);
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
