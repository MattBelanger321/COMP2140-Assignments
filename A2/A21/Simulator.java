
public class Simulator {

    public static boolean run(DFA dfa, String input) {
        String state = dfa.startState;

        for (int i = 0; i < input.length(); i++) {
            state = DFA.transitions.get(state + "_" + input.charAt(i));
        }

        return dfa.finalStates.contains(state);
    }

}
