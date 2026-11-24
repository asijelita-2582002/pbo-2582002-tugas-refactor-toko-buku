
public class EWallet implements MetodeBayar {

    @Override
    public String getNamaMetode() {
        return "E-Wallet (diskon 2%)";
    }

    @Override
    public int hitungTotalBayar(int totalBelanja) {
        return totalBelanja * 98 / 100;
    }
}
