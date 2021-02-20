package jill;

import junit.framework.TestCase;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * @author Jill W
 * @date 2021/01/08
 */
public class TestTest extends TestCase {


    public void testJFrame() {
        JFrame frame = new JFrame() {{
            add(new JPanel() {{
                add(new JLabel("Hey there") {{
                    setBackground(Color.black);
                    setForeground(Color.white);
                }});
                add(new JButton("Ok") {{
                    addActionListener(ae -> System.out.println("Button pushed"));
                }});
            }});
        }};
        assertNotNull(frame);
    }

    public void testFormat() {
        List<Integer> numbers = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        Map<String, String> codes = new HashMap<String, String>() {{
            put("1", "one");
            put("2", "two");
        }};
        System.out.println(numbers);
        System.out.println(codes);
        List<Integer> ints = Arrays.asList(1, 2, 3);
        assertNotNull(numbers);

    }

}