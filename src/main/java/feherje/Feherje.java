package feherje;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feherje {

    private final List<Character> feherjeLanc = new ArrayList<>();

    public void loadFromFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                feherjeLanc.add(line.charAt(0));
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not read file", ioException);
        }
    }

    public List<Character> getFeherjeLanc() {
        return List.copyOf(feherjeLanc);
    }

    public Map<Character, Integer> getAminosavOsszesito() {
        Map<Character, Integer> aminosavOsszesito = new HashMap<>();
        feherjeLanc
                .forEach(c -> aminosavOsszesito.merge(c, 1, Integer::sum));
        return aminosavOsszesito;
    }

    public Map<Character, Integer> getOsszegKeplet() {
        Map<Character, Integer> osszegKeplet = new HashMap<>();
        Aminosavak aminosavak = new Aminosavak();
        int feherjeHossz = feherjeLanc.size();

        getAminosavOsszesito().keySet()
                .forEach(character -> aminosavak.getAminosavByLetter(character)
                        .getAtomok()
                        .keySet()
                        .forEach(c -> osszegKeplet.merge(c, aminosavak.getAminosavByLetter(character).getAtomok().get(c) * getAminosavOsszesito().get(character), Integer::sum)));

        osszegKeplet.put('H', osszegKeplet.get('H') - (feherjeHossz - 1) * 2);
        osszegKeplet.put('O', osszegKeplet.get('O') - (feherjeHossz - 1));

        return osszegKeplet;
    }
}
