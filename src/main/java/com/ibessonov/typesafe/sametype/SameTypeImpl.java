package com.ibessonov.typesafe.sametype;

import java.util.function.Function;

/**
 * @author ibessonov
 */
class SameTypeImpl<T> extends SameType<T, T> {

    @Override
    public T castSecond(T r) {
        return r;
    }

    @Override
    public T castFirst(T l) {
        return l;
    }

    @Override
    public SameType<T, T> swap() {
        return this;
    }

    @Override
    public <Extra> SameType<T, Extra> transitive(SameType<T, Extra> prf) {
        return prf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <Left, Right> SameType<Left, Right> reverse(Function<SameType<Left, Right>, SameType<T, T>> f) {
        return (SameType) this;
    }

    @Override
    void inheritanceProtection() {}
}
