/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.douya.ui;

import android.support.v7.widget.RecyclerView;

public class LoadMoreAdapter extends MergeAdapter {

    private ProgressAdapter mProgressAdapter;

    public LoadMoreAdapter(RecyclerView.Adapter<?>... dataAdapters) {
        super(appendAdapter(dataAdapters, new ProgressAdapter()));

        RecyclerView.Adapter<?>[] adapters = getAdapters();
        mProgressAdapter = (ProgressAdapter) adapters[adapters.length - 1];
        updateHasProgressItem();
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                onChanged();
            }
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                onChanged();
            }
            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                onChanged();
            }
            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                onChanged();
            }
            @Override
            public void onChanged() {
                updateHasProgressItem();
            }
        });
    }

    private static RecyclerView.Adapter<?>[] appendAdapter(RecyclerView.Adapter<?>[] adapters,
                                                           RecyclerView.Adapter<?> adapter) {
        RecyclerView.Adapter<?>[] mergedAdapters = new RecyclerView.Adapter<?>[adapters.length + 1];
        System.arraycopy(adapters, 0, mergedAdapters, 0, adapters.length);
        mergedAdapters[adapters.length] = adapter;
        return mergedAdapters;
    }

    private void updateHasProgressItem() {
        int count = getItemCount() - mProgressAdapter.getItemCount();
        mProgressAdapter.setHasItem(count > 0);
    }

    public boolean isProgressVisible() {
        return mProgressAdapter.isProgressVisible();
    }

    public void setProgressVisible(boolean progressVisible) {
        mProgressAdapter.setProgressVisible(progressVisible);
    }
}
