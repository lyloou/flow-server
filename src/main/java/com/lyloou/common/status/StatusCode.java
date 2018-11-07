package com.lyloou.common.status;

import java.util.function.Supplier;

@FunctionalInterface
public interface StatusCode extends Supplier<Integer> {
    @Override
    Integer get();
}
