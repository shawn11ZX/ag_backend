package au.com.autogeneral.swagger.tasks;

import java.util.Stack;

/**
 * string balance tasks algorithm
 *
 * Brackets in a string are considered to be balanced if the following criteria are met:
 *
 * For every opening bracket (i.e., (, {, or [), there is a matching closing bracket (i.e., ), }, or ]) of the same
 * type (i.e., ( matches ), { matches }, and [ matches ]). An opening bracket must appear before (to the left of) its
 * matching closing bracket. For example, ]{}[ is not balanced.
 *
 * No unmatched braces lie between some pair of matched bracket. For example, ({[]}) is balanced, but {[}] and [{)]
 * are not balanced.
 *
 * @author Shawn Chang
 */
public class ValidationUtils {

    /**
     * stack based implementation
     * @param text
     * @return
     */
    public static boolean isBalanced(String text)
    {
        Stack<Character> stack = new Stack<>();
        for(Character c : text.toCharArray()){
            if (c == '{'  || c == '[' || c == '(') {
                stack.push(c);
            }
            if (c == '}' || c == ']' || c == ')') {
                if (stack.empty())
                    return false;
                Character top = stack.pop();
                boolean b = (c == '}' && top == '{' || c == ']' && top == '[' || c == ')' && top == '(');
                if (!b)
                    return false;
            }
        }
        return stack.empty();
    }
}
