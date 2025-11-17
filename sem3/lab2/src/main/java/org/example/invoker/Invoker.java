package org.example.invoker;

import org.example.annotation.Repeat;
import java.lang.reflect.*;
import java.util.Random;

public class Invoker {

    private static final Random random = new Random();

    public static void invokeAnnotatedMethods(Object obj) throws Exception {
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {

            if (!method.isAnnotationPresent(Repeat.class))
                continue;

            int mods = method.getModifiers();
            if (!Modifier.isProtected(mods) && !Modifier.isPrivate(mods))
                continue;

            method.setAccessible(true);

            int count = method.getAnnotation(Repeat.class).value();

            for (int i = 0; i < count; i++) {
                Object[] params = buildParams(method.getParameterTypes());
                method.invoke(obj, params);
            }
        }
    }

    private static Object[] buildParams(Class<?>[] types) throws Exception {
        Object[] arr = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            arr[i] = createValue(types[i]);
        }

        return arr;
    }

    private static Object createValue(Class<?> type) throws Exception {

        if (type == int.class || type == Integer.class)
            return random.nextInt(500);

        if (type == long.class || type == Long.class)
            return (long) random.nextInt(500);

        if (type == byte.class || type == Byte.class)
            return (byte) random.nextInt(20);

        if (type == double.class || type == Double.class)
            return random.nextDouble();

        if (type == float.class || type == Float.class)
            return random.nextFloat();

        if (type == boolean.class || type == Boolean.class)
            return random.nextBoolean();

        if (type == char.class || type == Character.class)
            return (char) ('a' + random.nextInt(26));

        if (type == String.class) {
            char c = (char) ('a' + random.nextInt(26));
            return "auto_text_" + c;
        }

        return createObjectRecursively(type);
    }

    private static Object createObjectRecursively(Class<?> type) throws Exception {
        if (type.isPrimitive())
            return 0;

        if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
            throw new IllegalArgumentException("You cant make an object from interface or abstract class =/");

        return createWithParams(type);
    }

    private static Object createWithParams(Class<?> type) throws Exception {
        Constructor<?> constructor;

        try {
            constructor = type.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            constructor = type.getDeclaredConstructors()[0];
        }

        constructor.setAccessible(true);

        Class<?>[] paramTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            params[i] = createValue(paramTypes[i]);
        }

        return constructor.newInstance(params);
    }


}
