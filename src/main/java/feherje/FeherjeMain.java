package feherje;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FeherjeMain {

    public static void main(String[] args) {
        Feherje bsa = new Feherje();

//      1. feladat
        Aminosavak aminosavak = new Aminosavak();

//      2.-3. feladat
        System.out.println("2-3. feladat");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("src", "main", "resources", "eredmeny.txt"))) {
            for (Aminosav aminosav : aminosavak.getAminosavList()) {
                String line = String.format("%s %d%n", aminosav.getRovidites(), aminosav.getRelativMolekulaTomeg());
                bufferedWriter.write(line);
                System.out.print(line);
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Con not write file", ioException);
        }
        System.out.println();


//      4. feladat
        System.out.println("4. feladat:");
        Path path = Path.of("src", "main", "resources", "bsa.txt");
        bsa.loadFromFile(path);
        System.out.println(bsa.getOsszegKeplet());
        System.out.println();


//      5. feladat
        List<Character> bsaFeherjeLanc = bsa.getFeherjeLanc();
        List<Character> hasitok = List.of('Y', 'W', 'F');
        int szamlalo = 0;
        int maximum = 0;
        int kezdoIndex = 1;
        int vegIndex = 1;
        for (int i = 0; i < bsaFeherjeLanc.size(); i++) {
            szamlalo++;
            if (hasitok.contains(bsaFeherjeLanc.get(i))) {
                if (maximum < szamlalo) {
                    maximum = szamlalo;
                    kezdoIndex = (i + 1) - szamlalo;
                    vegIndex = i;
                }
                szamlalo = 0;
            }
        }
        System.out.printf("Leghosszabb: %d", maximum);
        System.out.println();
        System.out.printf("Kezdő pozíció: %d", kezdoIndex);
        System.out.println();
        System.out.printf("Végső pozíció: %d", vegIndex);
        System.out.println();
        System.out.println();


//      6. feladat
        System.out.println("6. feladat");
        int index = 0;
        List<Character> alininOrValin = List.of('A', 'V');

        for (int i = 0; i < bsaFeherjeLanc.size(); i++) {
            if (bsaFeherjeLanc.get(i).equals('R') && alininOrValin.contains(bsaFeherjeLanc.get(i + 1))) {
                index = i;
                break;
            }
        }

        List<Character> hasitott = bsaFeherjeLanc.subList(0, index);
        int ciszteinSzamlalo = 0;
        for (Character character : hasitott) {
            if (character.equals('C')) ciszteinSzamlalo++;
        }

        System.out.printf("A hasítás után jelentkező első fehérjeláncban %d Cisztein található", ciszteinSzamlalo);
    }

}
