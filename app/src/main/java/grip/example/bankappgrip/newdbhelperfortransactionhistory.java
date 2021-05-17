package grip.example.bankappgrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class newdbhelperfortransactionhistory extends SQLiteOpenHelper {


    public newdbhelperfortransactionhistory(@Nullable Context context) {
        super(context, "transactions.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table transactionHistory"+"(id integer primary key autoincrement,sname text,amt integer,rname text,res integer)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS transactionHistory";
        db.execSQL(query);
        onCreate(db);
    }

    public void addRec(String sname, int amt, String rname, int res) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sname", sname);
        cv.put("amt", amt);
        cv.put("rname", rname);
        cv.put("res", res);

        float result = db.insert("transactionHistory",null,cv);
        db.close();
    }
    public Cursor readalldata()
    {SQLiteDatabase db =this.getWritableDatabase();
        String qry ="select * from transactionHistory";
        Cursor cursor=  db.rawQuery(qry,null);
        return cursor;

    }
}