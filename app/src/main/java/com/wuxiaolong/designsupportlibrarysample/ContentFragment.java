package com.wuxiaolong.designsupportlibrarysample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), setList());
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add(getActivity().getString(R.string.test_data) + i);
        }
        return dataList;

    }
}
