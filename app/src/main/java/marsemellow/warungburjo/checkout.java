package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class checkout extends AppCompatActivity {

    EditText etNama, etAlamat, etTelepon;
    TextView tvTotal;
    Button btnBuatPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String hargaTotal = data.getString("hargaTotal");
        String tulisan = "Harga total: Rp ";

        etNama = (EditText) findViewById(R.id.etNama);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        etTelepon = (EditText) findViewById(R.id.etTelepon);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnBuatPesanan = (Button) findViewById(R.id.btnBuatPesanan);

        tvTotal.setText(tulisan + hargaTotal);



        btnBuatPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new HttpAsyncTask().execute("http://192.168.43.172:10088/pjw/pesan.php");

            }
        });

    }



    public String POST(String url, Pemesan pemesan){
        InputStream inputStream = null;
        String result = "";
        krDatabaseHandler handler = new krDatabaseHandler(this);



        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("nama", pemesan.getNama());
            jsonObject.accumulate("alamat", pemesan.getAlamat());
            jsonObject.accumulate("telepon", pemesan.getTelepon());
            jsonObject.accumulate("pesanan", handler.isiKeJSONArray());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            Log.d("hasilnya", json);


            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();


            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            Pemesan pemesan = new Pemesan();
            pemesan.setNama(etNama.getText().toString());
            pemesan.setAlamat(etAlamat.getText().toString());
            pemesan.setTelepon(etTelepon.getText().toString());

            return POST(urls[0],pemesan);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}

