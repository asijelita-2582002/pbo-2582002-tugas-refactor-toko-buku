
public class Transfer implements MetodeBayar {

    @Override
    public String getNamaMetode() {
        return "Transfer (+Rp2.500)";
    }

    @Override
    public int hitungTotalBayar(int totalBelanja) {
        return totalBelanja + 2500;
    }
}
