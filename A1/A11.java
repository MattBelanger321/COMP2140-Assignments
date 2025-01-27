
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class A11 {
    //check whether the char is a letter

    enum states {
        INIT,
        ID,
        FUNC,
        QUOTES,
        COMMENTS
    }

    static boolean isLetter(int character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }

    // check whether the char is a letter or digit
    static boolean isLetterOrDigit(int character) {
        return isLetter(character) || (character >= '0' && character <= '9');
    }

    public static Set<String> getIdentifiers(String filename) throws Exception {

        String[] keywordsArray = {"IF", "WRITE", "READ", "RETURN", "BEGIN",
            "END", "MAIN", "INT", "REAL"};
        Set<String> keywords = new HashSet<>();
        Set<String> identifiers = new HashSet<>();
        keywords.addAll(Arrays.asList(keywordsArray));

        states state = states.INIT; // Initially it is in the INIT state. 
        states last = state;

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            code = code.append(line).append("\n");
        } // read the text line by line.
        code = code.append('$'); //add a special symbol to indicate the end of file.  

        int len = code.length();
        String token = "";
        for (int i = 0; i < len; i++) { //look at the characters one by one
            char next_char = code.charAt(i);
            switch (state) {
                case INIT -> {
                    if (isLetter(next_char)) {
                        state = states.ID;  // go to the ID state
                        token = token + next_char;
                    } else if (next_char == '\"') {
                        last = state;
                        state = states.QUOTES;
                    } else if (next_char == '/') {
                        if (len - i >= 2) {
                            if (code.charAt(i + 1) == '*' && code.charAt(i + 2) == '*') {
                                state = states.COMMENTS;
                                i += 2;
                            }
                        }
                    }// ignore everything else
                }
                case ID -> {
                    if (isLetterOrDigit(next_char)) { //take letter or digit if it is in ID state
                        token = token + next_char;
                    } else { // end of ID state
                        if (!keywords.contains(token)) {
                            identifiers.add(token);
                        }
                        token = "";
                        state = states.INIT;
                    }
                }
                case QUOTES -> {	// ignores the quoted strings
                    if (next_char == '\"') {
                        state = last;
                    }
                }
                case COMMENTS -> {
                    if (next_char == '*') {
                        if (len - i >= 2) {
                            if (code.charAt(i + 1) == '*' && code.charAt(i + 2) == '/') {
                                state = states.INIT;
                                i += 2;
                            }
                        }
                    }// ignore everything else
                }
                default -> {
                }
            }
        }
        return identifiers;
    }

    public static void main(String[] args) throws Exception {
        Set<String> ids = getIdentifiers("A1.tiny");
        for (String id : ids) {
            System.out.println(id);
        }
    }
}
