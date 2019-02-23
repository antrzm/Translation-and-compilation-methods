import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Automaton {
    private Map<Integer, Map<Character, Integer>> transitions;
    private int[] finalStates;

    Automaton() {
        this.transitions = new HashMap<>();
    }

    void readDescription(BufferedReader reader) throws IOException {
        String[] states = reader.readLine().split(" ");
        finalStates = new int[states.length];
        String transition = reader.readLine();
        for (int i = 0; i < states.length; i++) finalStates[i] = Integer.parseInt(states[i]);
        while (transition != null) {
            String[] subStrings = transition.split(" ");
            int stateNum = Integer.parseInt(subStrings[0]);
            Map<Character, Integer> event = transitions.computeIfAbsent(stateNum, k -> new HashMap<>());
            event.putIfAbsent(subStrings[1].charAt(0), Integer.parseInt(subStrings[2]));
            transition = reader.readLine();
        }

    }

    void calculate(String stringIn) {
        int state = 0;
        for (int i = 0; i < stringIn.length(); i++) {
            state = transitions.get(state).get(stringIn.charAt(i));
        }
        System.out.println(isFinal(state) ? "TRUE" : "FALSE");
    }

    private boolean isFinal(int state) {
        for (int finalState : finalStates) {
            if (state == finalState) return true;
        }
        return false;
    }

}
