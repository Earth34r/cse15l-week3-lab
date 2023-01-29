import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTests {
	@Test 
	public void testReverseInPlace() {
    int[] input1 = { 3 };
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{ 3 }, input1);

    input1 = new int[]{};
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{}, input1);

    input1 = new int[]{1, 2, 3, 4, 5};
    ArrayExamples.reverseInPlace(input1);
    assertArrayEquals(new int[]{5, 4, 3, 2, 1}, input1);
	}


  @Test
  public void testReversed() {
    int[] input1 = { };
    assertArrayEquals(new int[]{ }, ArrayExamples.reversed(input1));

    input1 = new int[]{1};
    assertArrayEquals(new int[]{1}, ArrayExamples.reversed(input1));

    input1 = new int[]{1, 2, 3, 4, 5};
    assertArrayEquals(new int[]{5, 4, 3, 2, 1}, ArrayExamples.reversed(input1));
  }

  @Test
  public void testAverageWithoutLowest() {
    double[] arr = {7, 2, 2, 3, 4, 5};
    assertTrue(ArrayExamples.averageWithoutLowest(arr) == 3.8);
  }

}
