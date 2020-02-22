import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomNumbers {
  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    for (int i = 1; i < 501; i++) {
      numbers.add(random.nextInt(1000));
    }
    System.out.println("The smallest number is " + Collections.min(numbers));
  }
}
