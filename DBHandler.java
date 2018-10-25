package com.matrix.morpheus.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    public static String strSeparator = "__,__";

    // Init DB version. Increase version on DB update!
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactInfo";

    // Table names
    private static final String TABLE_CONTACT = "contactDetails";

    // Table Columns names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "number";
    private static final String COLUMN_RELATION = "relation";


    //Here context passed will be of application and not activity.
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This method will be called every-time the file is called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Query to create table
        String CREATE_Person_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_CONTACT + "("
                + COLUMN_ID + " INTEGER (2) PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE + " INTEGER (8),"
                + COLUMN_RELATION+ " TEXT)";

        //Create table query executed in sqlite
        db.execSQL(CREATE_Person_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This method will be called only if there is change in DATABASE_VERSION.

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        // Create tables again
        onCreate(db);
    }


    // Helper utils
    public static String convertArrayToString(int[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            String elem = Integer.toString(array[i]);
            str = str+ elem;
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static int[] convertStringToArray(String str){
        String[] tmp = str.split(strSeparator);
        int[] arr = new int[tmp.length];
        for (int i=0; i<tmp.length; i++)
        {
            arr[i] = Integer.parseInt(tmp[i]);
        }
        return arr;
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Add new Contact
    public void addContact(person_detail person) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Content values use KEY-VALUE pair concept
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, person.getID());
        values.put(COLUMN_NAME, person.getName());
        values.put(COLUMN_PHONE, person.getNumber());
        values.put(COLUMN_RELATION, convertArrayToString(person.getRelation()));

        db.insert(TABLE_CONTACT, null, values);
        db.close();
    }

    // Getting single person details through ID
    public person_detail getPerson(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        //You can browse to the query method to know more about the arguments.
        Cursor cursor = db.query(TABLE_CONTACT,
                new String[] { COLUMN_ID, COLUMN_NAME, COLUMN_PHONE },
                COLUMN_ID + "=?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        person_detail person = new person_detail(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                convertStringToArray(cursor.getString(3)));

        //Return person
        return person;
    }

    // Getting All Person
    public List<person_detail> getAllPerson() {
        List<person_detail> personList = new ArrayList<person_detail>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                person_detail person = new person_detail(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        convertStringToArray(cursor.getString(3)));

                personList.add(person);
            } while (cursor.moveToNext());
        }

        // return person list
        return personList;
    }

    // Updating single person
    /*
    public int updateperson(person_detail person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, person.getpersonName());
        values.put(COLUMN_PHONE, person.getpersonEmail());

        // updating person row
        return db.update(TABLE_CONTACT,
                values,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(person.getpersonID())});

    }
    */

    // Deleting single person
    public void deletePerson(person_detail person) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT, COLUMN_ID + " = ?",
                new String[] { String.valueOf(person.getID()) });
        db.close();
    }


    // Getting Person count
    public int getPersonCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}