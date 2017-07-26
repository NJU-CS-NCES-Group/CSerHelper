package com.example.cserhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.cserhelper.R.id.listView;

/**
 * Created by zfwang on 2017/7/24.
 */

public class DownloadbyTimeFragment extends Fragment implements View.OnClickListener {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<DownloadMaterial> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sortDownloadMaterial();
        View view = inflater.inflate(R.layout.fragment_download_bytime, container, false);
        DownloadMaterialAdapter adapter = new DownloadMaterialAdapter(this.getContext(), R.layout.download_material_item, dataList);

        listView = (ListView) view.findViewById(R.id.list_view);
        //adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DownloadMaterial downloadmaterial = dataList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                //dialog.setTitle("TITlE");
                dialog.setMessage("请问您确定下载资料" + downloadmaterial.getName() + "吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                //Toast.makeText(getActivity(), downloadmaterial.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);
        Button courseDownloadButton = (Button) view.findViewById(R.id.course_download_button);
        courseDownloadButton.setOnClickListener(this);

        return view;
    }

    private void sortDownloadMaterial() {

        for (int i = 5; i < 10; i++) {
            DownloadMaterial material_item = new DownloadMaterial("Reference Book" + i + "", i * 1000 + "");
            dataList.add(material_item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.homeButton:
                startActivity(new Intent(this.getActivity(), PersonalCenterActivity.class));
                break;
            case R.id.course_download_button:
                replaceFragment(new DownloadFragment());
                break;
        }
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(this);
        transaction.add(R.id.fragment_download_time, fragment);
        transaction.commit();
    }
}
