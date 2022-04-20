package com.danikvitek.davilib.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Class for in-place {@link FunctionalInterface} execution
 */
@UtilityClass
public class CodeBlock {
    /**
     * @param supplier supplier to execute code block
     * @param <T> supplied type
     * @return supplier result
     */
    public <T> T supply(final @NotNull @NonNull Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * @param runnable runnable to execute
     */
    public void run(final @NotNull @NonNull Runnable runnable) {
        runnable.run();
    }

    /**
     * @param argument argument to pass in the function
     * @param function function to execute
     * @param <T> function result type
     * @param <R> function argument type
     * @return function result
     */
    public <T, R> R apply(T argument, final @NotNull Function<T, R> function) {
        return function.apply(argument);
    }
}
