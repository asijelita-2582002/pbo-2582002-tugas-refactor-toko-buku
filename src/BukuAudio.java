
public class BukuAudio extends Buku implements BisaDiunduh {

    private int durasiMenit;

    public BukuAudio(String judul, String penulis,
                     int harga, int durasiMenit) {
        super(judul, penulis, harga);
        this.durasiMenit = durasiMenit;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    @Override
    public String linkUnduh() {
        String slug = "kisah-programmer";

        return "[https://toko.buku/audio/" + slug
                + "](https://toko.buku/audio/" + slug + ")";
    }

    public int hargaAkhir() {
        return getHarga() * 90 / 100;
    }

    public String keterangan() {
        return durasiMenit + " menit, diskon 10%";
    }
}
