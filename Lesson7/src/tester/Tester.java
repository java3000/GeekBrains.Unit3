package tester;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tester {

    public static void start(Class clas) {
        Object testedClass = null;
        try {
            testedClass = clas.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //get
        List<Method> beforeSuiteMethod = getTestedMethods(clas, BeforeSuite.class);
        List<Method> afterSuiteMethod = getTestedMethods(clas, AfterSuite.class);
        List<Method> testMethods = getTestedMethods(clas, Test.class);

        //sort
        testMethods.sort(Comparator.comparingInt(x -> x.getAnnotation(Test.class).priority()));

        //check
        if ((!beforeSuiteMethod.isEmpty() || !afterSuiteMethod.isEmpty()) && (beforeSuiteMethod.size() > 1 || afterSuiteMethod.size() > 1))
            throw new RuntimeException("BeforeSuite or AfterSuite MUST be present, and ONLY once");

        //run
        executeTests(beforeSuiteMethod.get(0), testedClass);
        for (Method m : testMethods) executeTests(m, testedClass);
        executeTests(afterSuiteMethod.get(0), testedClass);

    }

    public static void start(String name) {
        try {
            start(Class.forName(name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //к последнему уж уроку захотелось хоть рая стримами попользоваться, а то все без них
    public static List<Method> getTestedMethods(Class clas, Class<? extends Annotation> annotation) {
        return Arrays.stream(clas.getDeclaredMethods())
                .filter(x -> x.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    private static void executeTests(Method method, Object obj, Object... args) {
        try {
            method.setAccessible(true);
            method.invoke(obj, args);
            method.setAccessible(false);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
