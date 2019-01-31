package au.com.autogeneral.swagger;

import au.com.autogeneral.swagger.validation.model.ValidationUtils;
import org.junit.Assert;
import org.junit.Test;

public class ValidationUtilsTest {

    @Test
    public void testEmptyBalanced() {
        Assert.assertTrue(ValidationUtils.isBalanced(""));
    }

    @Test
    public void testExtraNotBalanced() {
        Assert.assertFalse(ValidationUtils.isBalanced("("));
    }

    @Test
    public void testNestingNotBalanced() {
        Assert.assertFalse(ValidationUtils.isBalanced("([)]"));
    }

    @Test
    public void testNestingWithOtherCharNotBalanced() {
        Assert.assertFalse(ValidationUtils.isBalanced("(bba[d)d]"));
    }

    @Test
    public void testNestingBalanced() {
        Assert.assertTrue(ValidationUtils.isBalanced("{([])}"));
    }

    @Test
    public void testNestingWithOtherCharBalanced() {
        Assert.assertTrue(ValidationUtils.isBalanced("{a([s]b)d}"));
    }

    @Test
    public void testMultipleBalanced() {
        Assert.assertTrue(ValidationUtils.isBalanced("{{{}}}[]()"));
    }

    @Test
    public void testMultipleNotBalanced() {
        Assert.assertTrue(ValidationUtils.isBalanced("{{{}}}[()"));
    }
}