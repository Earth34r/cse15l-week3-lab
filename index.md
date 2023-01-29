# CSE 15L Week 3 Lab Report

Delaware Wade

Joe Politz

1/26/2023

---

## Part 1 - The StringServer

Below is the code for my StringServer. I was able to modify the code for NumberServer.java, but instead of keeping track of a number, my code uses a StringBuilder and adds to it repeatedly. 

![Untitled](CSE%2015L%20Week%203%20Lab%20Report%200fd6442eaa5c4cac8b62a3e4ec492c2d/Untitled.png)

![Untitled](CSE%2015L%20Week%203%20Lab%20Report%200fd6442eaa5c4cac8b62a3e4ec492c2d/Untitled%201.png)

The `handleRequest()` method is run by an instance of the Handler class, which is passed as an argument to the actual wavelet server code. The server code then calls `handleRequest()` each time a web request is made to the server. This method parses the url and performs the function of adding the string to the page.

![Untitled](CSE%2015L%20Week%203%20Lab%20Report%200fd6442eaa5c4cac8b62a3e4ec492c2d/Untitled%202.png)

Screenshot #1:

- Methods called: `Server.start()`, `handleRequest()`.
- Relevant method arguments: When `Server.start(port, new Handler())` is called, the port that is passed in from the command line and a new Handler object is instantiated. When `handleRequest(URI url)` is called, a URI is passed in for the method to parse.
- Relevant class fields and values: `StringBuilder messages` - this value is set to a string containing the value `"hello\nare you there\ni think it's working\nyes success :)\n"` . Another variable, `String[] parameters`, is equal to the following value: `{"s", "~"}`
- The value of `parameters[1]` (which is just a tilde `~`) is appended to `messages`, and the value of `messages` changes to: `"hello\nare you there\ni think it's working\nyes success :)\n~"`
    
    ---
    

![Untitled](CSE%2015L%20Week%203%20Lab%20Report%200fd6442eaa5c4cac8b62a3e4ec492c2d/Untitled%203.png)

Screenshot #2:

- Methods called: `handleRequest()`
- Relevant method arguments: `URI url` - the url being passed into the handler. In this case, the url is in the screenshot.
- Relevant class fields and values: `StringBuilder messages` - still contains the same string at the end of the last screenshot: `"hello\nare you there\ni think it's working\nyes success :)\n"` . The other variable, `String[] parameters`, is equal to the following value: `{"s", "another test - success again"}`
- Relevant class fields and values: `parameters[1]` has now been updated to `"another test - success again"`, and this String is appended to `messages`. The new value of `messages` reads: `"hello\nare you there\ni think it's working\nyes success :)\n~\nanother test - success again"`

## Part 2

- Below is a failure-inducing input for `ListExamples.filter()`. My StringChecker returns True if the given string contains the letter ‘c’.

```java
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
    
}
```

- The following JUnit test *doesn’t* induce a failure. The same StringChecker as above is used.

```java
@Test
public void testFilter2() {
	  List<String> list = new ArrayList<String>();
	  list.add("ice cream");
	  String[] a = {"ice cream"};
	
	  List<String> test = ListExamples.filter(list, this);
	  assertTrue(Arrays.asList(a).equals(test));
}
```

## The Symptom

![Untitled](CSE%2015L%20Week%203%20Lab%20Report%200fd6442eaa5c4cac8b62a3e4ec492c2d/Untitled%204.png)

Since the test for one item in the list passes, but the test for a list with multiple items fails, the items must be added in the wrong order.

## The Bug: Before and After

```java
// Returns a new list that has all the elements of the input list for which
// the StringChecker returns true, and not the elements that return false, in
// the same order they appeared in the input list;
static List<String> filter(List<String> list, StringChecker sc) {
  List<String> result = new ArrayList<>();
  for(String s: list) {
    if(sc.checkString(s)) {
      result.add(0, s);
    }
  }
  return result;
}
```

Line 15 is adding the checked strings to the beginning of the list instead of the end. This can be fixed by changing the line to `result.add(s)`.

```java
// Returns a new list that has all the elements of the input list for which
// the StringChecker returns true, and not the elements that return false, in
// the same order they appeared in the input list;
static List<String> filter(List<String> list, StringChecker sc) {
  List<String> result = new ArrayList<>();
  for(String s: list) {
    if(sc.checkString(s)) {
      result.add(s);
    }
  }
  return result;
}
```

All tests now pass, and the method functions properly!

## Part 3

---

I didn’t know that JUnit prints a line of dots for a passed test and an ‘E’ for a failed one. In the past I’ve only worked with Python’s unittest.py, which is set up quite differently. I also learned how to use the `Arrays.asList()` function!