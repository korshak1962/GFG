package general;

import java.util.List;
import java.util.function.Function;
import org.junit.Test;

public class LambdaTest {

  public int funConsumer(Function<Integer,Integer> fun, int par){
    return fun.apply(par);
  }
  @Test
  public void testConsumer(){
    System.out.println(funConsumer(x -> x*2, 5));
  }

  @Test
  public void test(){
    List<Integer> list= List.of(1,2,3,4,5,6,7,8,9,10);
    list.forEach(System.out::println);

    //System.out.println(list::toString);  don't expect interface
    Converter methodRef = Math::round;
     Converter lambda = x -> Math.round(x);
     System.out.println(methodRef.round(100.1)); // 100

    var str = "Zoo";
     StringStart methodRefInst = str::startsWith;
     StringStart lambdaInst = s -> str.startsWith(s);

     System.out.println(methodRefInst.beginningCheck("A")); // false

  }

}

@FunctionalInterface
interface Converter {
  long round(double num);
}

@FunctionalInterface
interface StringStart {
  boolean beginningCheck(String prefix);
}
