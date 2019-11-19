import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Problem.get_binding;
import static main.Problem.unify;

public class test {

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
}
