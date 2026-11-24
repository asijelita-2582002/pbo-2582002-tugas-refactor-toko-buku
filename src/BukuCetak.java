
public class BukuCetak extends Buku implements BisaDikirim {

    private int beratGram;

    public BukuCetak(String judul, String penulis,
                     int harga, int beratGram) {
        super(judul, penulis, harga);
        this.beratGram = beratGram;
    }

    @Override
    public int getBeratGram() {
        return beratGram;
    }

    public int hitungOngkir() {
        int kelipatan = (beratGram + 499) / 500;
        return kelipatan * 5000;
    }

    public int hargaAkhir() {
        return getHarga() + hitungOngkir();
    }

    public String keterangan() {
        return beratGram + " gram, ongkir "
                + formatRupiah(hitungOngkir());
    }

    private String formatRupiah(int angka) {
        return String.format("Rp%,d", angka).replace(',', '.');
    }
}
