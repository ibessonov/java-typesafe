package com.ibessonov.typesafe.base;

/**
 * @author ibessonov
 */
public final class Bottom {

    private Bottom() {
        throw new Impossible();
    }

    public <T> T bottom() {
        throw new Impossible();
    }
}
