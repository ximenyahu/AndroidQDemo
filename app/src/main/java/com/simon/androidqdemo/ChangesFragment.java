package com.simon.androidqdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChangesFragment extends BaseFragment {

    public static ChangesFragment sChangesFragment;

    public static ChangesFragment getInstance() {
        if (sChangesFragment == null) {
            sChangesFragment = new ChangesFragment();
        }
        return sChangesFragment;
    }


    private RecyclerView mRecyclerView;
    private List<String> data = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        initData();
        MyAdapter myAdapter = new MyAdapter(data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(myAdapter);
        return view;
    }

    private void initData() {
        data.add("ACCESS_BACKGROUND_LOCATION");
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public MyAdapter(List<String> data) {

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                TextView textView = itemView.findViewById(android.R.id.text1);
                textView.setText(data.get(0));
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                LocationPermissionFragment locationPermissionFragment = LocationPermissionFragment.getInstance();
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, locationPermissionFragment)
                        .addToBackStack("test")
                        .commit();
            }
        }
    }
}
