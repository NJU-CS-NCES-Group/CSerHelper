package com.example.cserhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.znq.zbarcode.CaptureActivity;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ClasscheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClasscheckFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    //private ImageView picture = (ImageView) View.findViewById(R.id.picture);
    private Uri imageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int QR_CODE = 2;
    public ClasscheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ClasscheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClasscheckFragment newInstance(String param1) {
        ClasscheckFragment fragment = new ClasscheckFragment();
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
        View view = inflater.inflate(R.layout.fragment_classcheck, container, false);
        Button button = (Button) view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);

        Button scanCode = (Button) view.findViewById(R.id.scan_code);
        scanCode.setOnClickListener(this);

        Button takePhoto = (Button) view.findViewById(R.id.take_photo);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.homeButton:
                startActivity(new Intent(this.getActivity(), PersonalCenterActivity.class));
                break;
            /*case R.id.take_photo:
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                break;*/
            case R.id.scan_code:
                    Intent intent = new Intent(this.getActivity(), CaptureActivity.class);
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
                    startActivityForResult(intent, QR_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_CODE) {
            if (resultCode == RESULT_OK) {
                //String contents = data.getStringExtra("SCAN_RESULT");
                //Toast.makeText(this.getContext(), contents + "", Toast.LENGTH_SHORT).show();
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }

};