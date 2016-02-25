package com.hujiang.designsupportlibrarydemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhongjiakang on 16/2/24.
 */
public class ContactFragment extends Fragment {
    RecyclerView view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (RecyclerView) inflater.inflate(R.layout.contact_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(new ContactViewAdapter(getActivity()));
    }
}
