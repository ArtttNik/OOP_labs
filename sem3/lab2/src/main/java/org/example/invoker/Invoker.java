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

            if (!Modifier.isProtected(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers()))
                continue;

            method.setAccessible(true);

            for (int i = 0; i < method.getAnnotation(Repeat.class).value(); i++) {
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

        if (type == int.class)
            return random.nextInt(500);
        if (type == String.class)
            return "Сгенерированный текст";

        return createObjectRecursively(type);
    }

    private static Object createObjectRecursively(Class<?> type) throws Exception {
        if (type.isPrimitive())
            return 0;

        if (type.isInterface() || Modifier.isAbstract(type.getModifiers()))
            throw new IllegalArgumentException("Нельзя создать объект для интерфейса или абстрактного класса");

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
