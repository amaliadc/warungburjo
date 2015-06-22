package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
import java.util.ArrayList;


public class DaftarMenu {
    public String idMakanan;
    public String namaMakanan;
    public String deskripsiMakanan;
    public String hargaMakanan;
    public String tersediaMakanan;

    public DaftarMenu(String idMakanan, String namaMakanan, String deskripsiMakanan, String hargaMakanan, String tersediaMakanan){
        this.idMakanan = idMakanan;
        this.namaMakanan = namaMakanan;
        this.deskripsiMakanan = deskripsiMakanan;
        this.hargaMakanan = hargaMakanan;
        this.tersediaMakanan = tersediaMakanan;
    }

    public static ArrayList<DaftarMenu> getMenus(){
        ArrayList<DaftarMenu> menus = new ArrayList<DaftarMenu>();
        return menus;
    }
}
