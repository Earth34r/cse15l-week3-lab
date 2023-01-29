import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListTests implements StringChecker {
    public boolean checkString(String s) {
        if (s.contains("c")) { return true; }
        return false;
    }

    @Test
    public void testFilter() {
        List<String> list = new ArrayList<String>();

        list.add("cheese");
        list.add("chicken");
        list.add("beans");
        list.add("rice");
        list.add("coleslaw");

        List<String> test = ListExamples.filter(list, this);
        String[] a = {"cheese", "chicken", "rice", "coleslaw"};
        assertTrue(test.get(0).equals("cheese"));
        assertTrue(Arrays.asList(a).equals(test));

    }

    @Test
    public void testFilter2() {
        List<String> list = new ArrayList<String>();
        list.add("ice cream");
        String[] a = {"ice cream"};

        List<String> test = ListExamples.filter(list, this);
        assertTrue(Arrays.asList(a).equals(test));

    }
    
}
