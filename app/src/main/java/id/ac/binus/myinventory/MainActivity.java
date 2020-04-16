package id.ac.binus.myinventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> itemArrayList;
    ViewAdapter objViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        itemArrayList = new ArrayList<>();
        showValuesFromDatabase();
    }

    public void showValuesFromDatabase()
    {
        try
        {
            DBAdapter objDBAdapter = new DBAdapter(this,null,null,1);
            SQLiteDatabase obj =  objDBAdapter.getReadableDatabase();
            if(obj!=null)
            {
                Cursor objCursor = obj.rawQuery("SELECT * from items",null );
                StringBuffer objStringBuffer = new StringBuffer();
                if(objCursor.getCount()==0)
                {
                    Toast.makeText(this,"No data is returned",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while(objCursor.moveToNext())
                    {
                        itemArrayList.add(new Item(objCursor.getInt(0),
                                objCursor.getString(1),
                                objCursor.getString(2),
                                objCursor.getString(3)));
                    }
                    objViewAdapter=new ViewAdapter(itemArrayList);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(objViewAdapter);
                }
            }
            else
            {
                Toast.makeText(this,"List is empty",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this,"showValuesFromDb"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.icon_add){
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
