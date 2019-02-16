import parser.Parser;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser;
        try {
            if (args.length > 0) {
                if (args[0].equals("-f")) {      //file case
                    File file = new File(args[0]);
                    Reader fileReader = new FileReader(file);
                    parser = new Parser(fileReader);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String arg : args) {
                        stringBuilder.append(arg);
                    }
                    Reader stringReader = new StringReader(stringBuilder.toString());
                    parser = new Parser(stringReader);
                }
                System.out.println("Result is: " + parser.calculate());
            } else {
                Scanner scanner = new Scanner(System.in);
                Reader cmdReader = new StringReader(scanner.nextLine());
                parser = new Parser(cmdReader);
                System.out.println("Result is: " + parser.calculate());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
