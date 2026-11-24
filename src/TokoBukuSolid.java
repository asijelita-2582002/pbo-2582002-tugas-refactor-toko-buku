
public class TokoBukuSolid {

    private StrukPrinter printer;
    private PenyimpananRiwayat penyimpananRiwayat;
    private PengirimNotifikasi pengirimNotifikasi;

    // Dependency Injection melalui constructor
    public TokoBukuSolid(StrukPrinter printer,
                         PenyimpananRiwayat penyimpanRiwayat,
                         PengirimNotifikasi pengirimNotifikasi) {
        this.printer = printer;
        this.penyimpananRiwayat = penyimpanRiwayat;
        this.pengirimNotifikasi = pengirimNotifikasi;
    }

    public void checkout(
            String pelanggan,
            String alamatEmail,
            MetodeBayar metodeBayar,
            Keranjang keranjang) {

        StringBuilder struk = new StringBuilder();

        struk.append("================ STRUK =================\n");
        struk.append("Pelanggan : ").append(pelanggan).append("\n");

        for (Buku buku : keranjang.getDaftarBuku()) {
            struk.append(buku.getJudul())
                    .append(" (")
                    .append(buku.getPenulis())
                    .append(")\n");

            struk.append("  Harga       : ")
                    .append(formatRupiah(buku.getHarga()))
                    .append("\n");

            struk.append("  Keterangan  : ")
                    .append(buku.keterangan())
                    .append("\n");

            struk.append("  Harga akhir : ")
                    .append(formatRupiah(buku.hargaAkhir()))
                    .append("\n");
        }

        int totalBelanja = keranjang.hitungTotal();
        int totalBayar = metodeBayar.hitungTotalBayar(totalBelanja);

        struk.append("-----------------------------------------\n");
        struk.append("Total belanja : ")
                .append(formatRupiah(totalBelanja)).append("\n");

        struk.append("Metode bayar  : ")
                .append(metodeBayar.getNamaMetode()).append("\n");

        struk.append("Total bayar   : ")
                .append(formatRupiah(totalBayar)).append("\n");

        struk.append("Berat kiriman : ")
                .append(keranjang.hitungTotalBerat())
                .append(" gram\n");

        struk.append("-------------- LINK UNDUH ---------------\n");

        for (BisaDiunduh buku : keranjang.getDaftarUnduh()) {
            struk.append(buku.linkUnduh()).append("\n");
        }

        struk.append("-----------------------------------------\n");

        // Mencetak struk
        printer.cetak(struk.toString());

        // Menyimpan riwayat transaksi
        penyimpananRiwayat.simpan(
                pelanggan + ";"
                        + keranjang.getDaftarBuku().size()
                        + " buku;"
                        + formatRupiah(totalBayar)
        );

        // Mengirim notifikasi
        pengirimNotifikasi.kirim(
                alamatEmail,
                "Terima kasih " + pelanggan
                        + ", total bayar " + formatRupiah(totalBayar)
        );
    }

    private String formatRupiah(int angka) {
        return String.format("Rp%,d", angka).replace(',', '.');
    }

    public static void main(String[] args) {

        // Membuat keranjang
        Keranjang keranjang = new Keranjang();

        // Membuat buku cetak
        BukuCetak buku1 = new BukuCetak(
                "Pemrograman Java", "John Doe", 120000, 750);

        BukuCetak buku2 = new BukuCetak(
                "Algoritma Dasar", "Jane Smith", 95000, 400);

        // Membuat buku digital
        BukuDigital buku3 = new BukuDigital(
                "Belajar OOP", "Ani Wijaya", 80000, 12.5);

        // Membuat buku audio
        BukuAudio buku4 = new BukuAudio(
                "Kisah Programmer", "Budi Santoso", 60000, 240);

        // Memasukkan buku ke keranjang
        keranjang.tambah(buku1);
        keranjang.tambahDikirim(buku1);

        keranjang.tambah(buku2);
        keranjang.tambahDikirim(buku2);

        keranjang.tambah(buku3);
        keranjang.tambahDiunduh(buku3);

        keranjang.tambah(buku4);
        keranjang.tambahDiunduh(buku4);

        // Menentukan metode pembayaran
        MetodeBayar metodeBayar = new EWallet();

        // Membuat implementasi dependency
        StrukPrinter printer = new PrinterKonsol();
        PenyimpananRiwayat riwayat = new FileRiwayat();
        PengirimNotifikasi email = new EmailSender();

        // Menjalankan transaksi
        TokoBukuSolid toko = new TokoBukuSolid(
                printer, riwayat, email);

        toko.checkout(
                "Sari",
                "sari@mail.com",
                metodeBayar,
                keranjang
        );
    }
}
