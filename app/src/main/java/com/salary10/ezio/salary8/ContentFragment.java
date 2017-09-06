package com.salary10.ezio.salary8;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salary10.ezio.salary8.R;

/**
 * Created by Jay on 2015/10/8 0008.
 */
public class ContentFragment extends Fragment {

    private TextView tv_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        String text = getArguments().getString("text");
        tv_content.setText(text);
        return view;
    }
}    