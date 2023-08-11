import parser.Parser;
import parser.ParserImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new ParserImpl();
        try {
            parser.startParse(new Scanner(new File("src/main/resources/code")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
