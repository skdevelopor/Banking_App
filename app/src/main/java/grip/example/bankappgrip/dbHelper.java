package grip.example.bankappgrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(@Nullable Context context) {
        super(context,"customerdb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table tbl_bank(id integer primary key autoincrement,name text,accbalance integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ="DROP TABLE IF EXISTS tbl_bank";
        db.execSQL(query);
        onCreate(db);
    }

    public  String addRec(String name,int accbal){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("name",name);
        cv.put("accbalance",accbal);
        float res = db.insert("tbl_bank", null, cv);
        if(res==-1){return "failed";}else{return "success";}

    }
    public Cursor readalldata()
    {SQLiteDatabase db =this.getWritableDatabase();
        String qry ="select * from tbl_bank";
        Cursor cursor=  db.rawQuery(qry,null);
        return cursor;

    }

   public Boolean updateaccountbal(int newbal,int id) {
        id=id+1;
ContentValues contentValues = new ContentValues();
contentValues.put("accbalance",newbal);
    SQLiteDatabase database =this.getWritableDatabase();
    database.update("tbl_bank",contentValues,"id = ?",new String[]{String.valueOf(id)});

return true;
   }
}




