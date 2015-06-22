package marsemellow.warungburjo;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    TextView tvIsConnected;
    ListView listViewMenu;

    ArrayList<DaftarMenu> arrayMenu = DaftarMenu.getMenus();
    CustomDaftarMenuAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMenu = (ListView) findViewById(R.id.listView_output);


        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        adapter = new CustomDaftarMenuAdapter(this, arrayMenu);



        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://192.168.1.3:10088/pjw/menu.php");

        final ListView listView = (ListView) findViewById(R.id.listView_output);
        listView.setAdapter(adapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                TextView a = (TextView) view.findViewById(R.id.textIdMakanan);
                String idMakanan = (String) a.getText();
                TextView b = (TextView) view.findViewById(R.id.textNamaMakanan);
                String namaMakanan = (String) b.getText();
                TextView c = (TextView) view.findViewById(R.id.textHargaMakanan);
                String hargaMakanan = (String) c.getText();
                TextView d = (TextView) view.findViewById(R.id.textDeskripsiMakanan);
                String deskripsiMakanan = (String) d.getText();
                TextView e = (TextView) view.findViewById(R.id.textTersediaMakanan);
                String tersediaMakanan = (String) e.getText();

                Intent intent = new Intent("com.kelompokamalia.burjo.pilih_makanan");

                Bundle data = new Bundle();
                data.putString("id", idMakanan);
                data.putString("nama", namaMakanan);
                data.putString("harga", hargaMakanan);
                data.putString("deskripsi", deskripsiMakanan);
                data.putString("tersedia", tersediaMakanan);

                intent.putExtras(data);

                startActivity(intent);
                //Toast.makeText(getBaseContext(), judul, Toast.LENGTH_LONG).show();


            }
        });

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
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

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            try {


                JSONArray menu = new JSONArray(result);

                String hasil = "";


                for (int i = 0; i < menu.length(); i++)
                {
                    String id = menu.getJSONObject(i).getString("id");
                    String nama = menu.getJSONObject(i).getString("nama_makanan");
                    String deskripsi = menu.getJSONObject(i).getString("deskripsi_makanan");
                    String harga = menu.getJSONObject(i).getString("harga_makanan");
                    String tersedia = menu.getJSONObject(i).getString("tersedia");

                    DaftarMenu newMenu = new DaftarMenu(id, nama, deskripsi, harga, tersedia);
                    adapter.add(newMenu);

                }



            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
