package com.example.cserhelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonalInformationFragment extends DialogFragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private boolean isEdit=false;
    TextView tvName,tvNumber;
    EditText etPhone,etEmail;
    Button btLeft,btRight,btclose;

    public PersonalInformationFragment() {
        // Required empty public constructor
    }

    public static PersonalInformationFragment newInstance(String param1) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_personal_information, container, false);
        tvName=(TextView)view.findViewById(R.id.studentName);
        tvNumber=(TextView)view.findViewById(R.id.studentNumber);
        etPhone=(EditText)view.findViewById(R.id.studentPhone);
        etEmail=(EditText)view.findViewById(R.id.studentEmail);
        btLeft=(Button)view.findViewById(R.id.leftButton);
        btRight=(Button)view.findViewById(R.id.rightButton);
        btclose=(Button)view.findViewById(R.id.closeButton);
        etEmail.setEnabled(false);
        etPhone.setEnabled(false);
        btLeft.setOnClickListener(this);
        btRight.setOnClickListener(this);
        btclose.setOnClickListener(this);
        setData(getStudentInformation());
        //setCancelable(false);
        return view;
    }
    //TODO:重写此函数实现数据传入显示
    public ArrayList<String> getStudentInformation()
    {
        ArrayList<String> information=new ArrayList<String>();
        information.add("计小科");
        information.add("151220117");
        information.add("1234567890");
        information.add("12345679@qq.com");
        return information;
    }
    //TODO:完成此函数实现数据保存并上传至服务器
    public void sendStudentInformation(ArrayList<String> inf)
    {
    }

    private void setData(ArrayList<String> inf)
    {
        tvName.setText(inf.get(0));
        tvNumber.setText(inf.get(1));
        etEmail.setText(inf.get(2));
        etPhone.setText(inf.get(3));
    }
    private void saveData()
    {
        ArrayList<String> inf=new ArrayList<String>();
        inf.add(tvName.getText().toString());
        inf.add(tvNumber.getText().toString());
        inf.add(etEmail.getText().toString());
        inf.add(etPhone.getText().toString());
        sendStudentInformation(inf);
    }
    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch(id) {
            case R.id.closeButton:
                dismiss();
                break;
            case R.id.leftButton: {
                if(isEdit==false) {
                    btLeft.setText("取消");
                    btRight.setText("保存");
                    etEmail.setEnabled(true);
                    etPhone.setEnabled(true);
                    isEdit=true;
                }
                else {
                    isEdit=false;
                    btLeft.setText("修改");
                    btRight.setText("注销");
                    etEmail.setEnabled(false);
                    etPhone.setEnabled(false);
                    setData(getStudentInformation());
                }
                break;
            }
            case R.id.rightButton:{
                if(isEdit==false) {
                    startActivity(new Intent(this.getActivity(),MainActivity.class));
                }
                else {
                    isEdit=false;
                    btLeft.setText("修改");
                    btRight.setText("注销");
                    etEmail.setEnabled(false);
                    etPhone.setEnabled(false);
                    saveData();
                }
            }
        }
    }

}
