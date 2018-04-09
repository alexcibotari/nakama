package com.alexcibotari.nakama.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClassUtils {

  protected ClassUtils() {
  }

  /**
   * Set if null.
   */
  public static <T> void setIfNotNull(final Supplier<T> getter, final Consumer<T> setter) {
    T t = getter.get();
    if (Objects.nonNull(t)) {
      setter.accept(t);
    }
  }
}
