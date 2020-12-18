package jill;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * @author Jill W
 * @date 2020/12/17
 */
public class Test {
    public static void main(String[] args) {
        Splitter on = Splitter.on(CharMatcher.breakingWhitespace());
    }
}
