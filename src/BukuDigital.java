
public class BukuDigital extends Buku implements BisaDiunduh {

    private double ukuranMB;

    public BukuDigital(String judul, String penulis,
                       int harga, double ukuranMB) {
        super(judul, penulis, harga);
        this.ukuranMB = ukuranMB;
    }

    public double getUkuranMB() {
        return ukuranMB;
    }

    @Override
    public String linkUnduh() {
        String slug = getJudul().toLowerCase().replace(' ', '-');
        return "[https://toko.buku/ebook/" + slug
                + "](https://toko.buku/ebook/" + slug + ")";
    }

    public int hargaAkhir() {
        return getHarga() * 80 / 100;
    }

    public String keterangan() {
        return ukuranMB + " MB, diskon 20%";
    }
}
