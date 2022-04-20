package com.danikvitek.davilib.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Various utils used for working with strings
 */
@UtilityClass
public class StringUtil {

    /**
     * @param what  string to filter by
     * @param where list to copy from
     * @return List of strings that contain {@code what}
     */
    public List<String> copyPartialInnerMatches(final @NotNull @NonNull String what,
                                                final @NotNull @NonNull List<String> where) {
        return where.stream().filter(s -> s.contains(what)).collect(Collectors.toList());
    }
}
