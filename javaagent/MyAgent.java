import java.lang.instrument.*;

public class MyAgent {
  public static void premain(String args, Instrumentation inst) {
    System.out.println("Instrumented");
  }
}
