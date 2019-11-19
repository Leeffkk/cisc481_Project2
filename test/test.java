import main.Problem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Problem.*;

public class test {

    ArrayList<ArrayList<ArrayList<String>>> PREMISES;


    @Test
    public void test_unify1(){
        ArrayList<String> pred1 = new ArrayList<>(){{add("p");add("?x");}};
        ArrayList<String> pred2 = new ArrayList<>(){{add("p");add("?y");}};
        HashMap<String, String> bindings = new HashMap<>();
        System.out.println(unify(pred1, pred2, bindings));
    }

    @Test
    public void test_unify2(){
        ArrayList<String> pred1 = new ArrayList<>(){{add("p");add("?x");add("?y");}};
        ArrayList<String> pred2 = new ArrayList<>(){{add("p");add("?z");add("?w");}};
        HashMap<String, String> bindings = new HashMap<>();
        bindings.put("?x", "?w");
        System.out.println(unify(pred1, pred2, bindings));
    }

    @Test
    public void test_bindings(){
        HashMap<String, String> test_bindings = new HashMap<>();
        test_bindings.put("?x", "?y");
        test_bindings.put("?y", "?z");
        test_bindings.put("?w", "?q");
        test_bindings.put("?z", "?w");
        System.out.println(get_binding("?x", test_bindings));
    }

    @Test
    public void test_uniquify(){
        ArrayList<ArrayList<String>> clause = new ArrayList<>();
        ArrayList<String> pred1 = new ArrayList<>(){{add("p");add("?x");add("?y");}};
        ArrayList<String> pred2 = new ArrayList<>(){{add("a");add("?x");add("?z");}};
        ArrayList<String> pred3 = new ArrayList<>(){{add("a");add("?z");add("?y");}};
        clause.add(pred1);
        clause.add(pred2);
        clause.add(pred3);
        var_counter.put("?x", 3);
        var_counter.put("?y", 6);
        var_counter.put("?z", 2);
        System.out.println(uniquify(clause));
    }

    @Test
    public void test_case1(){

    }












}
