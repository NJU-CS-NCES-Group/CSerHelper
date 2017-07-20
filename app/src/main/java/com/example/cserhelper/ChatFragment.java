package com.example.cserhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChatFragment extends Fragment implements View.OnClickListener{

    private SwipeMenuListView listView;
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private View view;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(String param1) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
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
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        listView=(SwipeMenuListView) view.findViewById(R.id.listView);
        Button button=(Button) view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);
        data=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            data.add("ceshi"+i);
        }
        adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                //deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.homeButton:
                startActivity(new Intent(this.getActivity(),PersonalCenterActivity.class));
        }
    }
}
