java -cp ./bin JLex.Main A3.lex
java java_cup.Main -parser A3Parser -symbols A3Symbol A3.cup
javac -cp ./bin:. -d ./bin A3.lex.java A3Parser.java A3Symbol.java A3User.java 
javac -cp ./bin:. A3ParserUser.java
java -cp ./bin:. A3ParserUser < A3.tiny