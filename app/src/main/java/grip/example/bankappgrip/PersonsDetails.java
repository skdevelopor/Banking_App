package grip.example.bankappgrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class PersonsDetails extends AppCompatActivity {
TextView dname,daccnumber,difsc,daccbal;
    String nameD;
    int AccBalD,indexofgiver2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dname = findViewById(R.id.Dname);
        daccnumber = findViewById(R.id.Dacccountnumber);
        daccbal = findViewById(R.id.DaccountBalance);
        difsc = findViewById(R.id.Difsc);

        Intent intent = getIntent();
        nameD = intent.getStringExtra("name");
        AccBalD = intent.getIntExtra("acc",0);
        indexofgiver2=intent.getIntExtra("indexxi",0);
                    dname.setText(nameD);
                    daccbal.setText(String.valueOf(AccBalD));
                    difsc.setText("CORP000 XXX");
                    daccnumber.setText("5300 XXXX XXXX");

    }
    public void TransferTo(View view){
        Intent intent =new Intent(PersonsDetails.this,Transfertowhom.class);
        intent.putExtra("indexofgiver",indexofgiver2);
        startActivity(intent);
        finish();

    }
}