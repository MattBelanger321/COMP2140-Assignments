
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;

class A12 {

    public static Set<String> getIdRegex(String filename) throws Exception {
        String[] keywordsArray = {"IF", "WRITE", "READ", "RETURN", "BEGIN", "END", "MAIN", "INT", "REAL"};
        Set<String> keywords = new HashSet<>();
        Set<String> identifiers = new HashSet<>();

        keywords.addAll(Arrays.asList(keywordsArray));

        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;

        Pattern idPattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
        Pattern commentPattern = Pattern.compile("/\\*\\*.*?\\*\\*/", Pattern.DOTALL);
        Pattern quotedStringPattern = Pattern.compile("\".*?\"|'.*?'");

        while ((line = br.readLine()) != null) {
            Matcher mQuotedString = quotedStringPattern.matcher(line);
            String lineWithoutQuotedStrings = mQuotedString.replaceAll("");

            Matcher mComments = commentPattern.matcher(lineWithoutQuotedStrings);
            String lineWithoutComments = mComments.replaceAll("");

            Matcher m = idPattern.matcher(lineWithoutComments);
            while (m.find()) {
                String id = lineWithoutComments.substring(m.start(), m.end());
                if (!keywords.contains(id)) {
                    identifiers.add(id);
                }
            }
        }

        br.close();
        return identifiers;
    }

    public static void main(String[] args) throws Exception {
        Set<String> ids = getIdRegex("A1.tiny");
        for (String id : ids) {
            System.out.println(id);
        }
    }
}
