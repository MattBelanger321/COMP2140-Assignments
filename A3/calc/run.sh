java -cp ./bin JLex.Main calc.lex
java java_cup.Main -parser CalcParser -symbols CalcSymbol calc.cup
javac -cp ./bin:. -d ./bin calc.lex.java
javac -cp ./bin:. -d ./bin  CalcParser.java CalcSymbol.java 
javac -cp ./bin:. CalcParserUser.java
java -cp ./bin:. CalcParserUser < calc.input