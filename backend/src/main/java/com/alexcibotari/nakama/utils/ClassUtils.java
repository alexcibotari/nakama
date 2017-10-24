package com.alexcibotari.nakama.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClassUtils {

    protected ClassUtils() { }

    public static  void setIfNotNull(final Supplier getter, final Consumer setter) {

       /* T t = getter.get();

        if (null != t) {
            setter.accept(t);
        }*/
    }
}
