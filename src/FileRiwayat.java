public class FileRiwayat implements PenyimpananRiwayat {

    @Override
    public void simpan(String baris) {
        System.out.println("[FILE riwayat.txt] " + baris);
    }
}