package grip.example.bankappgrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

public class customerlist extends AppCompatActivity implements Adaptrclass.itemClicked{

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ModelCustomer> customers;
    int flag=0;
dbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlist);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);





        recyclerView =findViewById(R.id.recyclerr);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customers= new ArrayList<ModelCustomer>();

dbHelper = new dbHelper(customerlist.this);
//________________________________________________________________________
   //customers= new ArrayList<ModelCustomer>();
    //    customers.add(new ModelCustomer("john",2300));
//________________________________________________________________________
//String result = dbHelper.addRec("sample1",100);
  //      String resulty = dbHelper.addRec("sample2",200);
/*
            dbHelper.addRec("garouv",100);
       dbHelper.addRec("george",900);
        dbHelper.addRec("lucifer",920);
        dbHelper.addRec("manoj",2920);
        dbHelper.addRec("paveen",930);
        dbHelper.addRec("riya",900);
        dbHelper.addRec("joseph",6880);
        dbHelper.addRec("yennifer",4882);
        dbHelper.addRec("lalith",852);
        dbHelper.addRec("saravanan",828);
*/

        Cursor cursor = new dbHelper(this).readalldata();

        while (cursor.moveToNext()){
            ModelCustomer obj = new ModelCustomer(cursor.getString(1),cursor.getInt(2));
            customers.add(obj);
        }

        adapter = new Adaptrclass(customers,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public Void itemOnClicked(int index) {

     //   Toast.makeText(customerlist.this,String.valueOf(index),Toast.LENGTH_SHORT).show();
         String name=   customers.get(index).getName();
         int Balance =   customers.get(index).getBal();
        Intent intent = new Intent(getApplicationContext(),PersonsDetails.class);
          intent.putExtra("name",name);
          intent.putExtra("acc",Balance);
          intent.putExtra("indexxi",index);

          Toast.makeText(customerlist.this,String.valueOf(index),Toast.LENGTH_SHORT).show();

        startActivity(intent);
        finish();
        return null;
    }



/*


   private void processinsert(String n, int account) {
        String res = new dbHelper(this).addRec(n,account);
        Toast.makeText(this,res,Toast.LENGTH_LONG).show();
}*/
public void th(View v){
    Intent intent= new Intent(customerlist.this,ZtransactionHistory.class);
    startActivity(intent);
}

}