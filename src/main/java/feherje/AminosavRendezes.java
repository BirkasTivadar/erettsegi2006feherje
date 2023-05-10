package feherje;

import java.util.Comparator;

public class AminosavRendezes implements Comparator<Aminosav> {
    @Override
    public int compare(Aminosav aminosav1, Aminosav aminosav2) {
        return aminosav1.getRelativMolekulaTomeg() - aminosav2.getRelativMolekulaTomeg();
    }
}
