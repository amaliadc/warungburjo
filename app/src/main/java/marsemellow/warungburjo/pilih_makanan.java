package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class pilih_makanan extends AppCompatActivity {

    TextView textNamaMakanan, textHargaMakanan, textDeskripsiMakanan, textIdMakanan;
    Button buttonPesan;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_makanan);

        Intent intent = getIntent();



        textNamaMakanan = (TextView) findViewById(R.id.textNamaMakanan);
        textHargaMakanan = (TextView) findViewById(R.id.textHargaMakanan);
        textDeskripsiMakanan = (TextView) findViewById(R.id.textDeskripsiMakanan);
        textIdMakanan = (TextView) findViewById(R.id.textIdMakanan);
        buttonPesan = (Button) findViewById(R.id.buttonPesan);

        Bundle data = intent.getExtras();

        final String namaMakanan = data.getString("nama");
        final String hargaMakanan = data.getString("harga");
        String deskripsiMakanan = data.getString("deskripsi");
        final String idMakanan = data.getString("id");
        String mataUang = "Rp ";
        String harga = hargaMakanan;

        mataUang += harga;

        textNamaMakanan.setText(namaMakanan);
        textHargaMakanan.setText(mataUang);
        textDeskripsiMakanan.setText(deskripsiMakanan);

        textIdMakanan.setText(idMakanan);

        new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute("http://192.168.43.172:10088/logo.png");

        buttonPesan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                Intent intent = new Intent("com.marsemellow.warungburjo.pesan_makanan");

                Bundle data = new Bundle();
                data.putString("id", idMakanan);
                data.putString("nama", namaMakanan);
                data.putString("harga", hargaMakanan);

                intent.putExtras(data);

                startActivity(intent);
            }
        });


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}

