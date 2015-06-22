package marsemellow.warungburjo;

/**
 * Created by acer-pc on 6/22/2015.
 */
public class Keranjang {
    private int _id;
    private String idMakanan;
    private String namaMakanan;
    private String hargaMakanan;
    private String jumlah;

    //public Keranjang(){};

    public Keranjang(String idMakanan, String namaMakanan, String hargaMakanan, String jumlah){
        super();
        this.idMakanan = idMakanan;
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.jumlah = jumlah;

    }

    public int get_id(){
        return _id;
    }

    public void set_id(int _id){
        this._id = _id;
    }

    public String getIdMakanan() {
        return idMakanan;
    }
    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }
    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getHargaMakanan() {
        return hargaMakanan;
    }
    public void setHargaMakanan(String hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public String getJumlah() {
        return jumlah;
    }
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    @Override
    public String toString() {
        return "Keranjang [_id=" + _id + ", idMakanan=" + idMakanan + ", namaMakanan=" + namaMakanan + ", hargaMakanan=" + hargaMakanan + ", jumlah=" + jumlah
                + "]";
    }


}

