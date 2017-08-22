package com.example.cserhelper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zfwang on 2017/7/25.
 */

public class DownloadMaterialAdapter extends ArrayAdapter<DownloadMaterial> {

    private int resourseId;

    public DownloadMaterialAdapter( Context context, int resource,
                                   List<DownloadMaterial> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownloadMaterial downloadMaterial = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
        TextView materialName = (TextView) view.findViewById(R.id.material_name);
        TextView materialTime = (TextView) view.findViewById(R.id.material_time);
        materialName.setText((CharSequence) downloadMaterial.getName());
        materialTime.setText((CharSequence) downloadMaterial.getUploadtime());
        return view;
    }
}
