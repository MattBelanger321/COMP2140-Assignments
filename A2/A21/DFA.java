
/**
 * DFA format:
 * alphabet
 * states
 * start state
 * final state(s)
 * transition_1
 * transition_2
 * ...
 * transition_n
 *
 * Example DFA for RE (a|b)(a|b|0|1)*
 * The diagram is
 *
 *  *a,b  a,b,0,1
 * A-->B---->
 * <----
 * The dfa.txt is:
 * a b 0 1
 * A B
 * A
 * B
 * A a B
 * A b B
 * B a B
 * B b B
 * B 0 B
 * B 1 B
 *
 * */
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class DFA {

    public static String[] alphabet;
    public String startState;
    public Set<String> finalStates;

    public static TreeMap<String, String> transitions = new TreeMap<>();

    /**
     * Construct a DFA from a text file
     */
    public DFA(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        alphabet = br.readLine().trim().split(" ");
        String[] states = br.readLine().split(" ");
        startState = br.readLine().trim();
        String[] finals = br.readLine().trim().split(" ");
        finalStates = new HashSet<>(Arrays.asList(finals));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] transition = line.trim().split(" ");
            transitions.put(transition[0] + "_" + transition[1], transition[2]);
        }
    }

    public static void main(String[] args) throws Exception {
        DFA dfa = new DFA(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(args[1]));
        String input = br.readLine().strip();
        br.close();
        boolean result = Simulator.run(dfa, input);
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[2]));
        bw.write(result + "");
        bw.close();
        System.out.println(input + "\t" + result);
    }
}
