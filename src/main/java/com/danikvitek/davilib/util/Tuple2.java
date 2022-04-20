package com.danikvitek.davilib.util;

import lombok.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @param <A> First element type
 * @param <B> Second element type
 */
@Value
public class Tuple2<A, B> {
    A first;
    B second;

    /**
     * Fabric method
     *
     * @param first first element
     * @param second second element
     * @param <A> first element type
     * @param <B> second element type
     * @return new Tuple2
     */
    @Contract("_, _ -> new")
    public static <A, B> @NotNull Tuple2<A, B> of(A first, B second) {
        return new Tuple2<>(first, second);
    }
}
