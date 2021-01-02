package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestSuite {
    public static void start(Class ... classes) throws RuntimeException {
        for (Class clss : classes) {

            Method[] methods = clss.getDeclaredMethods();
            ArrayList<Method> methodsToTest = new ArrayList<>();
            Method before = null;
            Method after = null;

            for (Method method : methods) {
                   if(method.getAnnotation(BeforeSuite.class) != null && before != null) {
                       throw new RuntimeException();
                   }
                   if (method.getAnnotation(BeforeSuite.class) != null) { before = method; }

                   if(method.getAnnotation(AfterSuite.class) != null && after != null) {
                       throw new RuntimeException();
                   }
                   if (method.getAnnotation(AfterSuite.class) != null) { after = method; }

                   if(method.getAnnotation(Test.class) != null) {
                       methodsToTest.add(method);
                   }
            }

            invoking(before, clss);

            testInvoke(methodsToTest, clss, 10);

            invoking(after, clss);

        }
    }

    private static void invoking (Method method, Class clss) {
        if(method != null) {
            try {
                method.setAccessible(true);
                method.invoke(clss.newInstance(), (Object []) method.getParameterTypes());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                System.out.println("error on invoking call");
            }
        }
    }

    private static void testInvoke (ArrayList<Method> methods, Class clss, int priority) {
        for (int i = 1; i <= priority; i++) {
            for (Method method : methods) {
                if(method.getAnnotation(Test.class).priority() == i) {
                    method.setAccessible(true);
                    try {
                        method.invoke(clss.newInstance(), (Object []) method.getParameterTypes());
                    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                        e.printStackTrace();
                        System.out.println("error in testInvoke");
                    }
                }
            }
        }
    }
}
