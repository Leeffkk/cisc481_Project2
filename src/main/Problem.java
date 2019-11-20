package main;

import java.util.*;

public class Problem {

    static final int MAX_RESOLUTIONS = 300;

    public static HashMap<String, Integer> var_counter = new HashMap<>();

    public static HashMap<String, String> unify(ArrayList<String> pred1,
                                                ArrayList<String> pred2,
                                                HashMap<String, String> bindings){
        HashMap<String, String> result = (HashMap<String, String>) bindings.clone();

        if(result == null || !pred1.get(0).equals(pred2.get(0)) || pred1.size()!=pred2.size()){
            return null;
        }
        if(pred1.equals(pred2)){
            return result;
        }
        for(int i=1; i<pred1.size(); i++) {
            String bind1 = get_binding(pred1.get(i), result);
            String bind2 = get_binding(pred2.get(i), result);
            if (bind1.equals(bind2)) {
                continue;
            }
            if (bind1.startsWith("?")) {
                result.put(bind1, bind2);
                continue;
            }
            if (bind2.startsWith("?")) {
                result.put(bind2, bind1);
                continue;
            }
            return null;
        }
        return result;
    }

    public static String get_binding(String var, HashMap<String, String> bindings){
        while (bindings.keySet().contains(var)){
            var = bindings.get(var);
        }
        return var;
    }

    public static ArrayList<ArrayList<String>> uniquify(ArrayList<ArrayList<String>> clause) {
        HashSet<String> has_changed = new HashSet<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (ArrayList<String> predicate : clause) {
            ArrayList<String> tmp_predicate = new ArrayList<>();
            for (String word : predicate) {
                if (word.startsWith("?")) {
                    if (has_changed.contains(word)) {
                        word += var_counter.get(word);
                    }
                    else if (!var_counter.containsKey(word)) {
                        var_counter.put(word, 1);
                        has_changed.add(word);
                        word += "1";
                    }
                    else {
                        var_counter.put(word, var_counter.get(word)+1);
                        has_changed.add(word);
                        word += var_counter.get(word);
                    }
                }
                tmp_predicate.add(word);
            }
            result.add(tmp_predicate);
        }
        return result;
    }

    public static void printPremises(ArrayList<ArrayList<ArrayList<String>>> premises){
        for (ArrayList<ArrayList<String>> clause : premises) {
            System.out.println(clause);
        }
    }

    public static HashSet<HashMap<String, String>> DFS(ArrayList<ArrayList<ArrayList<String>>> premises,
                           ArrayList<ArrayList<String>> goals){
        int counter = 0;
        HashMap<String, String> bindings = new HashMap<>();

        HashSet<HashMap<String, String>> result = new HashSet<>();

        Stack<Goal_n_Binding> open_list = new Stack<>();
        open_list.push(new Goal_n_Binding(goals, bindings));

        while (!open_list.isEmpty() && bindings!=null) {

            Goal_n_Binding cur_Goal_n_Binding = open_list.pop();

            ArrayList<ArrayList<String>> current_goal = cur_Goal_n_Binding.current_goal;
            HashMap<String, String> current_binding = cur_Goal_n_Binding.current_binding;

            for(ArrayList<ArrayList<String>> cur_clause : premises) {

                cur_clause = uniquify(cur_clause);
                counter++;
                HashMap<String, String> new_bindings = unify(current_goal.get(0),
                        cur_clause.get(0), current_binding);

                if (new_bindings == null) {
                    continue;
                }
                if (new_bindings != null) {
                    ArrayList<ArrayList<String>> successor = new ArrayList<>();

                    successor.addAll(cur_clause);
                    successor.remove(0);
                    ArrayList<ArrayList<String>> tmp_cur_goal = (ArrayList<ArrayList<String>>) current_goal.clone();
                    tmp_cur_goal.remove(0);
                    successor.addAll(tmp_cur_goal);

                    if (successor.isEmpty()) {
                        result.add(new_bindings);
                    }
                    if (!successor.isEmpty() && counter<=MAX_RESOLUTIONS) {
                        open_list.push(new Goal_n_Binding(successor, new_bindings));
                    }
                }
            }
        }
        return result;
    }

    public static HashSet<HashMap<String, String>> BFS(ArrayList<ArrayList<ArrayList<String>>> premises,
                                                       ArrayList<ArrayList<String>> goals){
        int counter = 0;
        HashMap<String, String> bindings = new HashMap<>();

        HashSet<HashMap<String, String>> result = new HashSet<>();

        Queue<Goal_n_Binding> open_list = new LinkedList<>();
        open_list.add(new Goal_n_Binding(goals, bindings));


        while (!open_list.isEmpty() && bindings!=null) {

            Goal_n_Binding cur_Goal_n_Binding = open_list.remove();

            ArrayList<ArrayList<String>> current_goal = cur_Goal_n_Binding.current_goal;
            HashMap<String, String> current_binding = cur_Goal_n_Binding.current_binding;

            for(ArrayList<ArrayList<String>> cur_clause : premises) {

                cur_clause = uniquify(cur_clause);
                counter++;
                HashMap<String, String> new_bindings = unify(current_goal.get(0),
                        cur_clause.get(0), current_binding);

                if (new_bindings == null) {
                    continue;
                }
                if (new_bindings != null) {
                    ArrayList<ArrayList<String>> successor = new ArrayList<>();

                    successor.addAll(cur_clause);
                    successor.remove(0);
                    ArrayList<ArrayList<String>> tmp_cur_goal = (ArrayList<ArrayList<String>>) current_goal.clone();
                    tmp_cur_goal.remove(0);
                    successor.addAll(tmp_cur_goal);

                    if (successor.isEmpty()) {
                        result.add(new_bindings);
                    }
                    if (!successor.isEmpty() && counter<=MAX_RESOLUTIONS) {
                        open_list.add(new Goal_n_Binding(successor, new_bindings));
                    }
                }
            }
        }
        return result;
    }
}