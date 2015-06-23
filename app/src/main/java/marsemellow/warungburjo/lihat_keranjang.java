package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class lihat_keranjang extends AppCompatActivity{

    Button btnCheckout;
    TextView tvTotal;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_keranjang);

        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        final krDatabaseHandler handler = new krDatabaseHandler(this);

        final String hargaTotal = handler.hitungTotal();

        String tulisan = "Harga total: Rp ";

        SQLiteDatabase db = handler.getWritableDatabase();

        Cursor todoCursor = db.rawQuery("SELECT  * FROM keranjang", null);

        krCursorAdapter todoAdapter = new krCursorAdapter(this, todoCursor);

        lvItems = (ListView) findViewById(R.id.listView_keranjang);

        lvItems.setAdapter(todoAdapter);

        tvTotal.setText(tulisan + hargaTotal);


        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.marsemellow.warungburjo.checkout");

                Bundle data = new Bundle();
                data.putString("hargaTotal", hargaTotal);
                intent.putExtras(data);


                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}

