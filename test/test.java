import main.Problem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Problem.*;

public class test {

    ArrayList<ArrayList<ArrayList<String>>> PREMISES = new ArrayList<>();
    ArrayList<ArrayList<String>> GOALS = new ArrayList<>();
    HashMap<String, String> BINDINGS = new HashMap<>();

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
        ArrayList<ArrayList<String>> clause1 = new ArrayList<>();
        clause1.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});
        clause1.add(new ArrayList<>(){{add("sister");add("?x");add("?z");}});
        clause1.add(new ArrayList<>(){{add("mother");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause2 = new ArrayList<>();
        clause2.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});
        clause2.add(new ArrayList<>(){{add("sister");add("?x");add("?z");}});
        clause2.add(new ArrayList<>(){{add("father");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause3 = new ArrayList<>();
        clause3.add(new ArrayList<>(){{add("sister");add("Mary");add("Sue");}});

        ArrayList<ArrayList<String>> clause4 = new ArrayList<>();
        clause4.add(new ArrayList<>(){{add("sister");add("Mary");add("Doug");}});

        ArrayList<ArrayList<String>> clause5 = new ArrayList<>();
        clause5.add(new ArrayList<>(){{add("father");add("Doug");add("John");}});

        ArrayList<ArrayList<String>> clause6 = new ArrayList<>();
        clause6.add(new ArrayList<>(){{add("mother");add("Sue");add("Paul");}});

        PREMISES.add(clause1);
        PREMISES.add(clause2);
        PREMISES.add(clause3);
        PREMISES.add(clause4);
        PREMISES.add(clause5);
        PREMISES.add(clause6);
        GOALS.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});

        printPremises(PREMISES);
        System.out.println("----------------------");
        System.out.println(GOALS);
    }

    @Test
    public void test_case2(){
        ArrayList<ArrayList<String>> clause1 = new ArrayList<>();
        clause1.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});
        clause1.add(new ArrayList<>(){{add("sister");add("?x");add("?z");}});
        clause1.add(new ArrayList<>(){{add("mother");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause2 = new ArrayList<>();
        clause2.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});
        clause2.add(new ArrayList<>(){{add("sister");add("?x");add("?z");}});
        clause2.add(new ArrayList<>(){{add("father");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause3 = new ArrayList<>();
        clause3.add(new ArrayList<>(){{add("sister");add("Mary");add("Sue");}});

        ArrayList<ArrayList<String>> clause4 = new ArrayList<>();
        clause4.add(new ArrayList<>(){{add("sister");add("Mary");add("Doug");}});

        ArrayList<ArrayList<String>> clause5 = new ArrayList<>();
        clause5.add(new ArrayList<>(){{add("father");add("Doug");add("John");}});

        ArrayList<ArrayList<String>> clause6 = new ArrayList<>();
        clause6.add(new ArrayList<>(){{add("mother");add("Sue");add("Paul");}});

        PREMISES.add(clause1);
        PREMISES.add(clause2);
        PREMISES.add(clause3);
        PREMISES.add(clause4);
        PREMISES.add(clause5);
        PREMISES.add(clause6);
        GOALS.add(new ArrayList<>(){{add("aunt");add("?x");add("?y");}});

        printPremises(PREMISES);
        System.out.println("----------------------");
        System.out.println(GOALS);
    }










}
