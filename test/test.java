import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static main.Problem.*;

public class test {

    ArrayList<ArrayList<ArrayList<String>>> PREMISES = new ArrayList<>();
    ArrayList<ArrayList<String>> GOALS = new ArrayList<>();
    HashSet<HashMap<String, String>> BINDINGS = new HashSet<>();

    @After
    public void after(){

        System.out.println("Premises: ");
        printPremises(PREMISES);
        System.out.println("----------------------");
        System.out.println("Goals:\n" + GOALS);

//        BINDINGS = DFS(PREMISES, GOALS);
        BINDINGS = BFS(PREMISES, GOALS);

        System.out.println("-------------------\nResult: ");
        for (HashMap<String, String> binding : BINDINGS) {
            System.out.print("{");
            for (String variable : GOALS.get(0)) {
                if (variable.startsWith("?")) {
                    System.out.print(variable + "/" + get_binding(variable, binding) + ", ");
                }
            }
            System.out.println("}");
        }

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
    }

    @Test
    public void test_case2(){
        ArrayList<ArrayList<String>> clause1 = new ArrayList<>();
        clause1.add(new ArrayList<>(){{add("simple_sentence");add("?x");add("?y");add("?z");add("?u");add("?v");}});
        clause1.add(new ArrayList<>(){{add("noun_phrase");add("?x");add("?y");}});
        clause1.add(new ArrayList<>(){{add("verb_phrase");add("?z");add("?u");add("?v");}});

        ArrayList<ArrayList<String>> clause2 = new ArrayList<>();
        clause2.add(new ArrayList<>(){{add("noun_phrase");add("?x");add("?y");}});
        clause2.add(new ArrayList<>(){{add("article");add("?x");}});
        clause2.add(new ArrayList<>(){{add("noun");add("?y");}});

        ArrayList<ArrayList<String>> clause3 = new ArrayList<>();
        clause3.add(new ArrayList<>(){{add("verb_phrase");add("?x");add("?y");add("?z");}});
        clause3.add(new ArrayList<>(){{add("verb");add("?x");}});
        clause3.add(new ArrayList<>(){{add("noun_phrase");add("?y");add("?z");}});

        ArrayList<ArrayList<String>> clause4 = new ArrayList<>();
        clause4.add(new ArrayList<>(){{add("article");add("a");}});

        ArrayList<ArrayList<String>> clause5 = new ArrayList<>();
        clause5.add(new ArrayList<>(){{add("article");add("the");}});

        ArrayList<ArrayList<String>> clause6 = new ArrayList<>();
        clause6.add(new ArrayList<>(){{add("noun");add("man");}});

        ArrayList<ArrayList<String>> clause7 = new ArrayList<>();
        clause7.add(new ArrayList<>(){{add("noun");add("dog");}});

        ArrayList<ArrayList<String>> clause8 = new ArrayList<>();
        clause8.add(new ArrayList<>(){{add("verb");add("likes");}});

        ArrayList<ArrayList<String>> clause9 = new ArrayList<>();
        clause9.add(new ArrayList<>(){{add("verb");add("bites");}});

        PREMISES.add(clause1);
        PREMISES.add(clause2);
        PREMISES.add(clause3);
        PREMISES.add(clause4);
        PREMISES.add(clause5);
        PREMISES.add(clause6);
        PREMISES.add(clause7);
        PREMISES.add(clause8);
        PREMISES.add(clause9);
        GOALS.add(new ArrayList<>(){{add("simple_sentence");add("?x");add("?y");add("?z");add("?u");add("?v");}});
    }

    @Test
    public void test_case3(){
        ArrayList<ArrayList<String>> clause1 = new ArrayList<>();
        clause1.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
        clause1.add(new ArrayList<>(){{add("parent");add("?x");add("?y");}});

        ArrayList<ArrayList<String>> clause2 = new ArrayList<>();
        clause2.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
        clause2.add(new ArrayList<>(){{add("ancestor");add("?x");add("?z");}});
        clause2.add(new ArrayList<>(){{add("parent");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause3 = new ArrayList<>();
        clause3.add(new ArrayList<>(){{add("parent");add("Mary");add("Paul");}});

        ArrayList<ArrayList<String>> clause4 = new ArrayList<>();
        clause4.add(new ArrayList<>(){{add("parent");add("Sue");add("Mary");}});

        PREMISES.add(clause1);
        PREMISES.add(clause2);
        PREMISES.add(clause3);
        PREMISES.add(clause4);
        GOALS.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
    }

    @Test
    public void test_case4(){
        ArrayList<ArrayList<String>> clause1 = new ArrayList<>();
        clause1.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
        clause1.add(new ArrayList<>(){{add("ancestor");add("?x");add("?z");}});
        clause1.add(new ArrayList<>(){{add("parent");add("?z");add("?y");}});

        ArrayList<ArrayList<String>> clause2 = new ArrayList<>();
        clause2.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
        clause2.add(new ArrayList<>(){{add("parent");add("?x");add("?y");}});

        ArrayList<ArrayList<String>> clause3 = new ArrayList<>();
        clause3.add(new ArrayList<>(){{add("parent");add("Mary");add("Paul");}});

        ArrayList<ArrayList<String>> clause4 = new ArrayList<>();
        clause4.add(new ArrayList<>(){{add("parent");add("Sue");add("Mary");}});

        PREMISES.add(clause1);
        PREMISES.add(clause2);
        PREMISES.add(clause3);
        PREMISES.add(clause4);
        GOALS.add(new ArrayList<>(){{add("ancestor");add("?x");add("?y");}});
    }

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

}
