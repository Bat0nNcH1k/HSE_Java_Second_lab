import java.io.*;
import java.util.HashMap;
import java.util.Map;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Character, Integer> LetterDict = new HashMap<>();
        String path;
        path = OpenFileInput();
        LetterDict = Counter(path, LetterDict);
        OpenFileOutput(LetterDict);
    }

    public static String OpenFileInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Print name of input file:");
        String pathin = scanner.nextLine();

        File fin = new File(pathin);
        if (!fin.exists()) {
            System.err.println("File " + pathin + " not found");
            System.exit(-1);
        } else {
            System.out.println("File " + pathin + " exists");
        }
        return pathin;
    }

    public static Map<Character, Integer> Counter(String path, Map<Character, Integer> LetterDict) throws FileNotFoundException {
        try (FileReader reader = new FileReader(path)) {
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isLetter(c) && Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                    char l = (char)c;
                    LetterDict.putIfAbsent(l,0);
                    LetterDict.put(l, LetterDict.get(l)+1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LetterDict;
    }

    public static void OpenFileOutput(Map<Character, Integer> LetterDict){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Print name of output file:");
        String pathout = scanner.nextLine();

        try (FileWriter writer = new FileWriter(pathout, false))
        {
            for (Map.Entry<Character, Integer> item: LetterDict.entrySet()) {
                writer.append(String.valueOf(item.getKey())).append("-").append(String.valueOf(item.getValue())).append("\n");
            }
            writer.flush();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}