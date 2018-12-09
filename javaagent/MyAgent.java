import java.lang.instrument.*;
import java.net.URL;
import java.net.URLClassLoader;
public class MyAgent {
  public static void premain(String args, Instrumentation inst) {
    System.out.println("Instrumented");
//    try {
//        inst.addTransformer(new FindClassTransformer(), true);
//    }
//    catch (NoClassDefFoundError e)
//    {
//        e.printStackTrace();
//    }

         Class[] a = inst.getAllLoadedClasses();
         for(int i = 0;i<a.length;i++)
         System.out.println( a[i].toString());

  }
}
