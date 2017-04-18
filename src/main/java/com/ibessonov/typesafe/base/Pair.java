package com.ibessonov.typesafe.base;

/**
 * @author ibessonov
 */
public class Pair<Left, Right> {

    public final Left left;
    public final Right right;

    private Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    public static <Left, Right> Pair<Left, Right> pair(Left left, Right right) {
        return new Pair<>(left, right);
    }
}
