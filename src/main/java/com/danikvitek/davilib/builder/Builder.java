package com.danikvitek.davilib.builder;

import org.jetbrains.annotations.NotNull;

interface Builder<T> {
    @NotNull T build();
}
