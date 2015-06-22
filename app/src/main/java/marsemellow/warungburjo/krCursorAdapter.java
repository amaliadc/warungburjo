package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class krCursorAdapter extends CursorAdapter {
    public krCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_keranjang, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView id = (TextView) view.findViewById(R.id.textNomor);
        TextView idM = (TextView) view.findViewById(R.id.textIdMakanan);
        TextView nama = (TextView) view.findViewById(R.id.textNamaMakanan);
        TextView harga = (TextView) view.findViewById(R.id.textHargaMakanan);
        TextView jumlah = (TextView) view.findViewById(R.id.textJumlah);
        // Extract properties from cursor

        int idV = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String idMV = cursor.getString(cursor.getColumnIndexOrThrow("idMakanan"));
        String namaV = cursor.getString(cursor.getColumnIndexOrThrow("namaMakanan"));
        String hargaV = cursor.getString(cursor.getColumnIndexOrThrow("harga"));
        String jumlahV = cursor.getString(cursor.getColumnIndexOrThrow("jumlah"));


        // Populate fields with extracted properties
        id.setText(String.valueOf(idV));
        idM.setText(idMV);
        nama.setText(namaV);
        harga.setText(hargaV);
        jumlah.setText(jumlahV);
    }
}
