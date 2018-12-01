package guopuran.bwie.com.app6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private static  Dao instance;
    private SqliteHelper helper;
    private SQLiteDatabase sb;
    public Dao(Context context){
        helper=new SqliteHelper(context);
        sb=helper.getReadableDatabase();
    }
    public static Dao getInstance(Context context){
        if (instance==null){
            instance=new Dao(context);
        }
        return instance;
    }
    public void add(String name){
        ContentValues values=new ContentValues();
        values.put("name",name);
        sb.insert("users",null,values);
    }
    public List<String> select(){
        List<String> list=new ArrayList<>();
        Cursor cursor = sb.query("users", null, null, null, null, null, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                list.add(name);
            }
            cursor.close();
        }
        return list;
    }
    public void del(){
        sb.delete("users",null,null);
    }
}
