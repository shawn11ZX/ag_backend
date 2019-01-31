package au.com.autogeneral.swagger.validation;

import java.util.Stack;

public class ValidationUtils {
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
                if (c == '}' && top == '{' || c == ']' && top == '[' || c == ')' && top == '(' )
                    return true;
                return false;
            }
        }
        return stack.empty();
    }
}
