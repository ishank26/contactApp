package com.matrix.morpheus.contactlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;


public class ContactListFragment extends Fragment {
    public ListView listView;
    public ContactListAdapter mAdapter;
    private ArrayList<person_detail> itemList = new ArrayList<person_detail>();
    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_list_layout, container, false);
        listView = (ListView) rootView.findViewById(R.id.contact_list);
        int[] tmp = {1};
        itemList.add(new person_detail(R.drawable.ic_launcher_background ,"The Grey" , 2011, tmp));
        mAdapter = new ContactListAdapter(getActivity(),itemList);
        listView.setAdapter(mAdapter);
        return rootView;
    }
}
