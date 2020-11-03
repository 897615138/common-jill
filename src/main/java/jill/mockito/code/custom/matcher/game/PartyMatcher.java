package jill.mockito.code.custom.matcher.game;

import jill.mockito.code.ArgumentMatcher;

import java.util.function.Function;

/**
 * @author JillW
 * @date 2020/09/30
 */
public class PartyMatcher<T> extends ArgumentMatcher<T> implements org.mockito.ArgumentMatcher<T> {
    private final Object value;
    private final Function<T, Object> function;

    PartyMatcher(Function<T, Object> getProperty, Object value) {
        this.value = value;
        function = getProperty;
    }

    public static <F> PartyMatcher<F> partyMatcher(Function<F, Object> getProperty, Object value) {
        return new PartyMatcher<>(getProperty, value);
    }

    @Override
    public boolean matches(T o) {
        return function.apply(o).equals(value);
    }
}