package guopuran.bwie.com.app7;

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
    //单例
    public static Dao getInstance(Context context){
        if (instance==null){
            instance=new Dao(context);
        }
        return instance;
    }
    //添加
    public void add(Bean bean){
        ContentValues values=new ContentValues();
        values.put("name",bean.getName());
        values.put("pass",bean.getPass());
        sb.insert("users",null,values);
    }
    //查询
    public List<Bean> select(){
        List<Bean> list=new ArrayList<>();
        Cursor cursor = sb.query("users", null, null, null, null, null, null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String pass = cursor.getString(cursor.getColumnIndex("pass"));
                Bean bean=new Bean(name,pass);
                list.add(bean);
            }
            cursor.close();
        }
        return list;
    }
    //删除
    public void del(String name){
        sb.delete("users","name=?",new String[]{name});
    }
}
