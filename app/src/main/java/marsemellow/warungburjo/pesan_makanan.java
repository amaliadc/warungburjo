package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class pesan_makanan extends AppCompatActivity {

    TextView textNamaMakanan, textIdMakanan;
    Button buttonKeranjangBelanja, buttonLihatKeranjangBelanja;
    EditText editTextJumlah;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesan_makanan);

        final krDatabaseHandler db = new krDatabaseHandler(this);



        Intent intent = getIntent();

        textNamaMakanan = (TextView) findViewById(R.id.textNamaMakanan);
        textIdMakanan = (TextView) findViewById(R.id.textIdMakanan);

        buttonKeranjangBelanja = (Button) findViewById(R.id.buttonKeranjangBelanja);
        buttonLihatKeranjangBelanja = (Button) findViewById(R.id.buttonLihatKeranjangBelanja);

        editTextJumlah = (EditText) findViewById(R.id.editTextJumlah);

        Bundle data = intent.getExtras();

        final String namaMakanan = data.getString("nama");
        final String idMakanan = data.getString("id");
        final String hargaMakanan = data.getString("harga");



        textNamaMakanan.setText(namaMakanan);

        textIdMakanan.setText(idMakanan);


        buttonKeranjangBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jumlah = editTextJumlah.getText().toString();

                if (!jumlah.isEmpty()) {
                    Keranjang newKB = new Keranjang(idMakanan,namaMakanan,hargaMakanan,jumlah);
                    db.addKeranjang(newKB);


                }
            }
        });

        buttonLihatKeranjangBelanja.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent("com.marsemellow.warungburjo.lihat_keranjang");

                startActivity(intent);
            }
        });


    }
}
