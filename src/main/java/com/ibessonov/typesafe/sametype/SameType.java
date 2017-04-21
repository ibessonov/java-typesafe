package com.ibessonov.typesafe.sametype;

import java.util.function.Function;

/**
 * Object of {@code SameType<Left, Right>} ensures type equality of {@code Left} & {@code Right} and provides
 * type-safe cast operations as well as combination operations
 * @param <Left> first type
 * @param <Right> second type
 * @author ibessonov
 */
public abstract class SameType<Left, Right> {

    /**
     * The only actual way to create new {@code SameType} instance
     * @param <T> single type parameter for both first and second types
     * @return new {@code SameType} instance that equalize type with itself
     */
    public static <T> SameType<T, T> sameType() {
        return new SameTypeImpl<>();
    }

    /**
     * Safely cast object of type {@code Left} to type {@code Right}
     * @param l object to cast
     * @return same object but with different type
     */
    public abstract Right castFirst(Left l);

    /**
     * Safely cast object of type {@code Right} to type {@code Left}
     * @param r object to cast
     * @return same object but with different type
     */
    public abstract Left castSecond(Right r);

    /**
     * @return {@code SameType} object with its type parameters swapped
     */
    public abstract SameType<Right, Left> swap();

    /**
     * Changes type of second parameter to the extra one if it's proven that they are equal
     * @param prf proof that type {@code Right} is equal to type {@code Extra}
     * @param <Extra> type that is equal to {@code Right}
     * @return {@code prf} object casted to proper type
     */
    public abstract <Extra> SameType<Left, Extra> transitive(SameType<Right, Extra> prf);

    /**
     * Reverses {@code SameType} transformation function. This means that since
     * {@code SameType<Left2, Right2> -> SameType<Left, Right>} transformation is bijective we must be able to deduce
     * {@code SameType<Left2, Right2>} object from current {@code SameType<Left, Right>} one
     * @param f function that maps {@code SameType<Left2, Right2>} to {@code SameType<Left, Right>}
     * @param <Left2> first type in {@code f}'s argument
     * @param <Right2> first type in {@code f}'s argument
     * @return {@code this} object but with updated type parameters
     */
    public abstract <Left2, Right2> SameType<Left2, Right2> reverse(Function<SameType<Left2, Right2>, SameType<Left, Right>> f);

    /**
     * Package-protected method in abstract class guarantees that one cannot inherit this class outside the package
     */
    abstract void inheritanceProtection();
}
