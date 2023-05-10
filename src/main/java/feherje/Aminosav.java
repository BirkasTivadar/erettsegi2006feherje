package feherje;

import java.util.HashMap;
import java.util.Map;

public class Aminosav {
    private final String rovidites;
    private final Character betujel;

    public static final Map<Character, Integer> ATOM_TOMEGEK = Map.of('C', 12, 'H', 1, 'O', 16, 'N', 14, 'S', 32);
    private final Map<Character, Integer> atomok;

    private int relativMolekulaTomeg;

    public Aminosav(String rovidites, Character betujel, Map<Character, Integer> atomok) {
        this.rovidites = rovidites;
        this.betujel = betujel;
        this.atomok = atomok;
        computeRelativMolekulaTomeg();
    }

    private void computeRelativMolekulaTomeg() {
        relativMolekulaTomeg = atomok.keySet().stream()
                .mapToInt(key -> atomok.get(key) * ATOM_TOMEGEK.get(key))
                .sum();
    }

    public String getRovidites() {
        return rovidites;
    }

    public Character getBetujel() {
        return betujel;
    }

    public Map<Character, Integer> getAtomok() {
        return new HashMap<>(atomok);
    }

    public int getRelativMolekulaTomeg() {
        return relativMolekulaTomeg;
    }
}
