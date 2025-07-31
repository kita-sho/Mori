package src.main.jp.ac.ksu.mori.utility;

import java.util.stream.Stream;

/*
 * 末尾再帰のインターフェースです
 */
@FunctionalInterface
public interface TailCall<T> {

    TailCall<T> apply();

    default boolean isComplete() {
        return false;
    }

    default T getResult() {
        throw new RuntimeException("Not implemented");
    }
    
    default T call() {
        return Stream.iterate(this, TailCall::apply)
                .filter(TailCall::isComplete)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unreachable"))
                .getResult();
    }
}


