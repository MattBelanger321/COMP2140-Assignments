
import java.util.*;
%%
%{    
    int numbers = 0;
    int lines = 0;
    int comments = 0;
    int quotes = 0;
    int ids = 0;

    private static java.io.FileReader reader;
    private static java.io.FileWriter writer;
    Set<String> keywords = Set.of("IF", "WRITE", "READ", "RETURN", "BEGIN", "END", "MAIN", "INT", "REAL");


    public static void main(String argv[]) throws java.io.IOException {
        // Initialize the reader and writer as class variables
        reader = new java.io.FileReader("A2.input");
        writer = new java.io.FileWriter("A2.output");

        A2Lexer yy = new A2Lexer(reader);

        while (true) {
            yy.yylex();
        }
    }
%}

%notunix
%type void
%class A2Lexer
%state COMMENT
%state DQSTRING
%state SQSTRING
%eofval{

    writer.write("numbers: " + numbers);
    writer.write("\ncoments: " + comments);
    writer.write("\nlines: " + lines);
    writer.write("\nquotedString: " + quotes);
    writer.write("\nidentifiers: " + ids);

    writer.close();
    reader.close();
    System.exit(0);
%eofval}

IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*

%% 
<YYINITIAL>"\"" {yybegin(DQSTRING);}
<YYINITIAL>"'" {yybegin(SQSTRING);}
<YYINITIAL>"/**" {yybegin(COMMENT);}
<YYINITIAL>{IDENTIFIER} { 
                            String text = yytext();
                            if(!keywords.contains(text)){
                                ids++;
                            }
                        }
<YYINITIAL>[0-9][0-9]*(\.[0-9][0-9]*)? {numbers++;}


<COMMENT>"**/" {
    comments++;
    yybegin(YYINITIAL);
}
<DQSTRING>"\"" {
    quotes++;
    yybegin(YYINITIAL);
}
<SQSTRING>"'" {
    quotes++;
    yybegin(YYINITIAL);
}

\r|\n {lines++;}
. {}