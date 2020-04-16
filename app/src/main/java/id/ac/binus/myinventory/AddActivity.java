package id.ac.binus.myinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etName,etDescription,etQuantity;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etName = findViewById(R.id.et_name);
        etDescription = findViewById(R.id.et_description);
        etQuantity = findViewById(R.id.et_quantity);
        btnSubmit = findViewById(R.id.btn_submit);

        if(getIntent().getExtras()!=null)
        {
            etName.setText(getIntent().getStringExtra("name"));
            etDescription.setText(getIntent().getStringExtra("description"));
            etQuantity.setText(getIntent().getStringExtra("quantity"));
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getApplicationContext(),null,null,1);
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                String quantity = etQuantity.getText().toString();
                Item item = new Item(name,description,quantity);
                dbAdapter.onOpen();

                if(getIntent().getExtras()!=null)
                {
                    dbAdapter.updateItems(getIntent().getLongExtra("id",0),name,description,quantity);
                    Toast.makeText(getApplicationContext(),"Successfully edit an item",Toast.LENGTH_SHORT).show();
                }
                else{
                    dbAdapter.insertItem(item);
                    Toast.makeText(getApplicationContext(),"Successfully add an item",Toast.LENGTH_SHORT).show();
                }

                dbAdapter.close();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
