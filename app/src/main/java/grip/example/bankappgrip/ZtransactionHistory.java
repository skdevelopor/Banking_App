package grip.example.bankappgrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;

public class ZtransactionHistory extends AppCompatActivity implements ZtransactionAdapter.itemClicked {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Zmodel> trans;
    newdbhelperfortransactionhistory db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ztransaction_history);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        db = new newdbhelperfortransactionhistory(ZtransactionHistory.this);
        recyclerView =findViewById(R.id.recyclert);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Boolean v;
        trans = new ArrayList<Zmodel>();
        //   db.addRec("sample2",500,"sample2",0);
      //  Log.i("errorzz","1");

        Cursor cursor = new newdbhelperfortransactionhistory(this).readalldata();
        while (cursor.moveToNext()){
            if(cursor.getInt(4)==0){
                v=false;
            }
           else
               v=true;

          Zmodel obj = new Zmodel(cursor.getString(1),cursor.getString(3),cursor.getInt(2),v);

          trans.add(obj);

        }


        //trans.add(new Zmodel("john","ravi",500,true));
        //trans.add(new Zmodel("john","ravi",500,true));


        adapter = new ZtransactionAdapter(trans,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Void itemOnClicked(int index) {
        return null;
    }
}