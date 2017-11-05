package com.example.cserhelper;

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
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    SwipeMenuListView listView;
    List<MessageItem> messageList;
    MessageAdapter adapter;

    List<MessageItem> getList(){
        List<MessageItem> ret=new ArrayList<MessageItem>();
        ret.add(new MessageItem("李武军",1,"该交作业了！"));
        ret.add(new MessageItem("王芷芙",2,"该交作业了！"));
        ret.add(new MessageItem("",0,"该交作业了！"));
        ret.add(new MessageItem("李武军",1,"该交作业了！"));
        return ret;
    }

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(String param1) {
        MessageFragment fragment = new MessageFragment();
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
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        Button button=(Button)view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);
        listView=(SwipeMenuListView) view.findViewById(R.id.messageView);
        listView.addHeaderView(new ViewStub(this.getActivity()));
        messageList = getList();
        adapter = new MessageAdapter(messageList,this.getActivity());
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
                        //删除该item
                        messageList.remove(position);
                        int t=listView.getFirstVisiblePosition();
                        if (t != messageList.size() - 1) t++;
                        listView.setAdapter(adapter);
                        listView.setSelection(t);
                        break;
                }
                return false;
            }
        });
        listView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
