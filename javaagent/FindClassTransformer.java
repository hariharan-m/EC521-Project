import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FindClassTransformer implements ClassFileTransformer {

    public FindClassTransformer() {
        super();
    }

    public byte[] transform(ClassLoader loader, String className, Class redefiningClass, ProtectionDomain domain, byte[] bytes) throws IllegalClassFormatException {
        byte[] byteCode = bytes;
        System.out.println("Loading class: " + className);
        return byteCode;
    }
}