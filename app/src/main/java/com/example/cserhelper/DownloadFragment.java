package com.example.cserhelper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DownloadFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    /*add by Sunny*/
    private ListView listView;
    private ArrayAdapter<String> adapter;
    public List<DownloadMaterial> dataList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    //private String mParam1;
    public DownloadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DownloadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DownloadFragment newInstance(String param1) {
        DownloadFragment fragment = new DownloadFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDownloadMaterial();
    }

    private void initDownloadMaterial() {
        dataList.clear();
        for (int i = 0; i < 5; i++) {
            DownloadMaterial material_item = new DownloadMaterial("Reference Book" + i + "", i * 1000 + "");
            dataList.add(material_item);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*add by Sunny*/
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        DownloadMaterialAdapter adapter = new DownloadMaterialAdapter(this.getContext(), R.layout.download_material_item, dataList);
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DownloadMaterial downloadmaterial = dataList.get(position);
                Toast.makeText(getActivity(), downloadmaterial.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) view.findViewById(R.id.homeButton);
        button.setOnClickListener(this);
        Button timeDownloadButton = (Button) view.findViewById(R.id.time_download_button);
        timeDownloadButton.setOnClickListener(this);
        return view;
        //return inflater.inflate(R.layout.fragment_download, container, false);
    }
    
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.homeButton:
                startActivity(new Intent(this.getActivity(),PersonalCenterActivity.class));
                break;
            case R.id.time_download_button:
                replaceFragment(new DownloadbyTimeFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_download, fragment);
        transaction.commit();
    }
}
