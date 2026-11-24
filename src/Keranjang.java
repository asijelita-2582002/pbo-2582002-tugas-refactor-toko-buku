
import java.util.ArrayList;
import java.util.List;

public class Keranjang {

    private final ArrayList<Buku> daftarBuku = new ArrayList<>();
    private final ArrayList<BisaDikirim> daftarKirim = new ArrayList<>();
    private final ArrayList<BisaDiunduh> daftarUnduh = new ArrayList<>();

    public void tambah(Buku buku) {
        daftarBuku.add(buku);
    }

    public void tambahDikirim(BisaDikirim buku) {
        daftarKirim.add(buku);
    }

    public void tambahDiunduh(BisaDiunduh buku) {
        daftarUnduh.add(buku);
    }

    public List<Buku> getDaftarBuku() {
        return List.copyOf(daftarBuku);
    }

    public List<BisaDikirim> getDaftarKirim() {
        return List.copyOf(daftarKirim);
    }

    public List<BisaDiunduh> getDaftarUnduh() {
        return List.copyOf(daftarUnduh);
    }

    public int hitungTotal() {
        int total = 0;

        for (Buku buku : daftarBuku) {
            total += buku.hargaAkhir();
        }

        return total;
    }

    public int hitungTotalBerat() {
        int totalBerat = 0;

        for (BisaDikirim buku : daftarKirim) {
            totalBerat += buku.getBeratGram();
        }

        return totalBerat;
    }
}
