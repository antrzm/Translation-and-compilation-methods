import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2){
            System.err.println("Wrong args!\nShould be: <description file> <string file>");
            System.exit(-1);
        }
        try{
        Automaton automaton = new Automaton();
        automaton.readDescription(new BufferedReader(new FileReader(args[0])));
        automaton.calculate(new String(Files.readAllBytes(Paths.get(args[1]))));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
