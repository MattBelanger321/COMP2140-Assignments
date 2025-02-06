
import java.io.*;

class A3ParserUser {

    public static void main(String[] args) throws Exception {
        A3Parser parser = new A3Parser(new A3Scanner(System.in));
        Integer result = (Integer) parser.parse().value;
        FileWriter fw = new FileWriter(new File("A3.output"));
        fw.write(" Number of methods: " + result.intValue());
        fw.close();
    }
}
