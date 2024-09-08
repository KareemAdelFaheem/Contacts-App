package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context ctx;
    private static final String DATABASE_NAME="contacts.db";
    private static final int DATABASE_Version=1;

    private static final String TABLE_NAME="contacts_info";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="contact_name";
    private static final String COLUMN_number="contact_number";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query=
                "CREATE TABLE "+TABLE_NAME+
                        " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME +" TEXT ," +
                        COLUMN_number +" TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addContact(String name,String number){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_number,number);
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(ctx,"Contact add failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ctx,"Contact added to database successfully",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        Cursor cursor=null;
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
    public void deleteData(String row_name){
        SQLiteDatabase db =this .getWritableDatabase();
       long result= db.delete(TABLE_NAME,COLUMN_NAME+" = ? ", new String[]{row_name});
             if(result==-1){
                 Toast.makeText(ctx,"Failed to delete contact",Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(ctx,"Contact deleted from database successfully",Toast.LENGTH_SHORT).show();

             }
    }

}
