package grip.example.bankappgrip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Transfertowhom extends AppCompatActivity implements Adaptrclass.itemClicked {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ModelCustomer> customers;
    ArrayList<Zmodel> trans;
    int getterIndex;
    int indexofgiver;
    newdbhelperfortransactionhistory db;
    Button ok;
    EditText editText;
    TextView textView;
    Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfertowhom);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        db = new newdbhelperfortransactionhistory(Transfertowhom.this);
        indexofgiver = intent.getIntExtra("indexofgiver",0);
        recyclerView =findViewById(R.id.recylerwhom);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customers= new ArrayList<ModelCustomer>();
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
        getterIndex= index;
        dialogue();
        return null;
    }
    public void dialogue(){
        AlertDialog.Builder builder= new AlertDialog.Builder(Transfertowhom.this);
        builder.setTitle("amount");
        View view=getLayoutInflater().inflate(R.layout.amt_to_be,null);
        editText= view.findViewById(R.id.editTextNumber3);
        textView = view.findViewById(R.id.textView5);
         ok=view.findViewById(R.id.ok);
         cancel = view.findViewById(R.id.cancel);
         builder.setView(view);
         AlertDialog alertDialog = builder.create();



//________________________________________________________________________________________-
        ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
if(editText.getText().toString().trim().equals(""))
{
    editText.setError("Please Enter a valid amount");
    editText.requestFocus();
}



else{


    if (customers.get(indexofgiver).getBal() < Integer.parseInt(editText.getText().toString())) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Transfertowhom.this);
        builder1.setTitle("insufficient Amount").setMessage("click cancel to stop the transaction or ok to change the amount");


        builder1.setPositiveButton("continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder1.setNegativeButton("cancel your transaction", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                int amt = Integer.parseInt(editText.getText().toString());
                db.addRec(customers.get(indexofgiver).getName(), amt, customers.get(getterIndex).getName(), 0);
                adapter.notifyDataSetChanged();
                Intent inty = new Intent(Transfertowhom.this, customerlist.class);
                startActivity(inty);



            }
        });
        builder1.show();

    }


    if (customers.get(indexofgiver).getBal() >= Integer.parseInt(editText.getText().toString())) {
        int p1 = customers.get(indexofgiver).getBal();
        int p2 = customers.get(getterIndex).getBal();
        int amt = Integer.parseInt(editText.getText().toString());

        int p1bal = p1 - amt;
        int p2bal = p2 + amt;

        Log.i("Azxbalold1", String.valueOf(p1));
        Log.i("Azxbalold2", String.valueOf(p2));

        Log.i("Azxbalnew1", String.valueOf(p1bal));
        Log.i("Azxbalnew2", String.valueOf(p2bal));


        if (indexofgiver != getterIndex) {
            boolean res = new dbHelper(Transfertowhom.this).updateaccountbal(p1bal, indexofgiver);
            boolean resy = new dbHelper(Transfertowhom.this).updateaccountbal(p2bal, getterIndex);

            customers.get(indexofgiver).setBal(p1bal);
            customers.get(getterIndex).setBal(p2bal);
            db.addRec(customers.get(indexofgiver).getName(), amt, customers.get(getterIndex).getName(), 1);

            adapter.notifyDataSetChanged();

        } else if (indexofgiver == getterIndex) {

            customers.get(indexofgiver).setBal(p1);
            db.addRec(customers.get(indexofgiver).getName(), amt, customers.get(getterIndex).getName(), 1);

            adapter.notifyDataSetChanged();
        }



        Intent inty = new Intent(Transfertowhom.this, customerlist.class);
        startActivity(inty);  }


}



                    }








                });



        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Transfertowhom.this,customerlist.class));

            }
        });




    }

    }
