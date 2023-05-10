package feherje;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aminosavak {
    private final List<Aminosav> aminosavlist = new ArrayList<>();

    public Aminosavak() {
        this.loadFromFile();
    }

    public void loadFromFile() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src", "main", "resources", "aminosav.txt"))) {
            String line;
            int i = 0;
            String rovidites = "";
            Character betujel = null;
            Map<Character, Integer> atomok = new HashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                switch (i) {
                    case 0 -> rovidites = line;
                    case 1 -> betujel = line.charAt(0);
                    case 2 -> atomok.put('C', Integer.parseInt(line));
                    case 3 -> atomok.put('H', Integer.parseInt(line));
                    case 4 -> atomok.put('O', Integer.parseInt(line));
                    case 5 -> atomok.put('N', Integer.parseInt(line));
                    case 6 -> atomok.put('S', Integer.parseInt(line));
                    default -> System.out.println("Nincs ilyen betűjelű atom az aminosavakban");
                }
                if (i == 6) {
                    aminosavlist.add(new Aminosav(rovidites, betujel, atomok));
                    atomok = new HashMap<>();
                    i = -1;
                }
                i++;
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not read file", ioException);
        }
    }

    public List<Aminosav> getAminosavList() {
        aminosavlist.sort(new AminosavRendezes());
        return new ArrayList<>(aminosavlist);
    }

    public Aminosav getAminosavByLetter(Character character) {
        return aminosavlist.stream()
                .filter(aminosav -> aminosav.getBetujel().equals(character))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nincs ilyen betűjelű aminosav."));
    }
}
