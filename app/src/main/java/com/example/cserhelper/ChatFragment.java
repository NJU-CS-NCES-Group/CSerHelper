package com.example.cserhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        data = new ArrayList<String>();
        for(int i = 0; i < 15; i++)
        {
            data.add("ceshi" + i);
        }

        //replaceFragment(new ChatFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        Button button = (Button) view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);

        Button chatButton = (Button) view.findViewById(R.id.chat_button);
        chatButton.setOnClickListener(this);
        //replaceFragment(new ChatFragment());

        adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,data);
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
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index)
                {
                    case 0:
                        data.remove(position);
                        //adapter.notifyDataSetChanged();
                        int t=listView.getFirstVisiblePosition();
                        if(t!=data.size()-1) t++;
                        listView.setAdapter(adapter);
                        listView.setSelection(t);
                }
                return false;
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.homeButton:
                startActivity(new Intent(this.getActivity(), PersonalCenterActivity.class));
                break;
            case R.id.chat_button:
                replaceFragment(new InstantmsgFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_chat, fragment);
        transaction.commit();
    }
}
