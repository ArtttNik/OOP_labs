package org.example.invoker;

import org.example.target.MyClass;
import org.example.annotation.Repeat;

import java.lang.reflect.Method;
import java.util.Random;
import java.lang.reflect.Modifier;

public class Invoker {

    private static final Random random = new Random();

    public static void invokeAnnotatedMethods(MyClass obj) throws Exception {
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                int mod = method.getModifiers();
                if (!Modifier.isProtected(mod) && !Modifier.isPrivate(mod))
                    continue;

                Repeat rep = method.getAnnotation(Repeat.class);
                int times = rep.times();

                method.setAccessible(true);

                for (int i = 0; i < times; i++) {
                    Class<?>[] paramTypes = method.getParameterTypes();
                    Object[] params = new Object[paramTypes.length];

                    for (int j = 0; j < paramTypes.length; j++) {
                        if (paramTypes[j] == int.class)
                            params[j] = random.nextInt(500);
                        else if (paramTypes[j] == String.class)
                            params[j] = "тест";
                        else
                            params[j] = null;
                    }

                    method.invoke(obj, params);
                }
            }
        }
    }
}
