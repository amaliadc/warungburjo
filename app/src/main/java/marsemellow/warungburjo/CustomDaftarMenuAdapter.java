package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomDaftarMenuAdapter extends ArrayAdapter<DaftarMenu> {
    public  CustomDaftarMenuAdapter(Context context, ArrayList<DaftarMenu> menus){
        super(context, 0, menus);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        DaftarMenu menu = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_makanan, parent, false);
        }

        TextView textNamaMakanan = (TextView) convertView.findViewById(R.id.textNamaMakanan);
        TextView textDeskripsiMakanan = (TextView) convertView.findViewById(R.id.textDeskripsiMakanan);
        TextView textHargaMakanan = (TextView) convertView.findViewById(R.id.textHargaMakanan);
        TextView textIdMakanan = (TextView) convertView.findViewById(R.id.textIdMakanan);
        TextView textTersediaMakanan = (TextView) convertView.findViewById(R.id.textTersediaMakanan);

        textNamaMakanan.setText(menu.namaMakanan);
        textDeskripsiMakanan.setText(menu.deskripsiMakanan);
        textHargaMakanan.setText(menu.hargaMakanan);
        textIdMakanan.setText(menu.idMakanan);
        textTersediaMakanan.setText(menu.tersediaMakanan);

        return convertView;
    }
}

