package com.matrix.morpheus.contactlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;


public class ContactDetailFragment extends Fragment {
    DBHandler db;

    public ContactDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Creating object to use DBHandler
        db = new DBHandler(getContext());
        addDataToDatabase();
        readDataFromDatabase();
    }



    public void addContact(View v) {
        // Get data from view
        person_detail newperson;
        HashMap<String, Integer> hmap = newperson.getHash();
        EditText new_name = (EditText) v.findViewById(R.id.NameBox);
        String name = new_name.getText().toString();
        newperson.setName(name);
        EditText new_phone = (EditText) v.findViewById(R.id.PhoneBox);
        String phonee = new_phone.getText().toString();
        int phone = Integer.parseInt(phonee);
        newperson.setNumber(phone);
        ListView relationList = (ListView) v.findViewById(R.id.RelationList);
        String[] relations = getItemsFromList(relationList);
        int[] relationids;
        for (String item: relations){
            relationids.add(hmap.get(item));
        }
        newperson.setRelation(relationids);
        /*itemsAdapter.add(task);
        new_title.setText("");
        new_description.setText("");*/
    }

    private void readDataFromDatabase() {

        // Reading all students
        Log.d("MainActivity", "Reading all students..");
        List<StudentModel> students = db.getAllStudents();

        for (StudentModel i : students) {
            String log = "Id: " + i.getStudentID() + " ,Name: " + i.getStudentName() + " ,Email: " + i.getStudentEmail();
            // Writing students to log
            Log.d("MainActivity", log);
        }

    }

    private void addDataToDatabase() {
        // Inserting Students
        Log.d("MainActivity", "Inserting ..");
        db.addStudent(new StudentModel(234567, "Rajat", "rajat@gmail.com"));
        db.addStudent(new StudentModel(955689, "ABC", "abc@gmail.com"));
        db.addStudent(new StudentModel(345129, "XYZ", "xyz@gmail.com"));
    }
}
    public String[] getItemsFromList(ListView listname){
        SparseBooleanArray checked = listname.getCheckedItemPositions();
        List<String> nameList = new List<String>;
        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i) == true) {
                String name = String.valueOf(listname.getItemAtPosition(checked.keyAt(i)));
                nameList.add(name)
            }
        }
        return nameList;
    }

}
