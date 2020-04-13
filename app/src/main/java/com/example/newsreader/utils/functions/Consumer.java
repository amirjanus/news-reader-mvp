package com.example.newsreader.utils.functions;

/**
 * Interface user for callbacks that accepts a single input argument and returns no result.
 *
 * @param <T> Type of the input.
 */
@FunctionalInterface
public interface Consumer<T> {
    
    /**
     * Performs this operation on the given argument.
     *
     * @param t Input argument.
     */
    void accept( T t );
    
}
