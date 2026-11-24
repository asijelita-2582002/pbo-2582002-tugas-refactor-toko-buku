
public class Tunai implements MetodeBayar {

    @Override
    public String getNamaMetode() {
        return "Tunai";
    }

    @Override
    public int hitungTotalBayar(int totalBelanja) {
        return totalBelanja;
    }
}
