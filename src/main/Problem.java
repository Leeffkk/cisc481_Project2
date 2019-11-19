package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Problem {


    public static HashMap<String, String> unify(ArrayList<String> pred1,
                                                ArrayList<String> pred2,
                                                HashMap<String, String> bindings){
        if(bindings == null || !pred1.get(0).equals(pred2.get(0)) || pred1.size()!=pred2.size()){
            return null;
        }
        if(pred1.equals(pred2)){
            return bindings;
        }
        for(int i=1; i<pred1.size(); i++) {
            String bind1 = get_binding(pred1.get(i), bindings);
            String bind2 = get_binding(pred2.get(i), bindings);
            if(!bind1.equals(bind2) && bindings.get(bind1) != bind2) {
                bindings.put(bind1, bind2);
            }
        }
        return bindings;
    }

    public static String get_binding(String var, HashMap<String, String> bindings){
        while (bindings.keySet().contains(var)){
            var = bindings.get(var);
        }
        return var;
    }
}