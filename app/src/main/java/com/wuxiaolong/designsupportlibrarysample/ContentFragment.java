package com.wuxiaolong.designsupportlibrarysample;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {
    private boolean isVisible;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private List<String> mDataList = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private String mTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = this.getArguments().getString("title");
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDataList);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mDataList.clear();
                setList();

            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }

    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible) {
            return;
        }
        setList();
    }

    protected void onInvisible() {
    }

    protected void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int j = i; j < i + 20; j++) {
                    mDataList.add(mTitle + "\n" + getActivity().getString(R.string.test_data) + j);
                }
                i = i + 20;
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 2000);

    }

    int i = 0;

    private void setList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (i = 0; i < 20; i++) {
                    mDataList.add(mTitle + "\n" + getActivity().getString(R.string.test_data) + i);
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 2000);


    }
}
