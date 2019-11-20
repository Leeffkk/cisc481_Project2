package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Goal_n_Binding {
    ArrayList<ArrayList<String>> current_goal;
    HashMap<String, String> current_binding;

    public Goal_n_Binding(ArrayList<ArrayList<String>> goal, HashMap<String, String> binding) {
        current_goal = goal;
        current_binding = binding;
    }

    @Override
    public String toString(){
        return "\tgoal: " + current_goal.toString() + "\n\tbinding: " + current_binding.toString();
    }
}
