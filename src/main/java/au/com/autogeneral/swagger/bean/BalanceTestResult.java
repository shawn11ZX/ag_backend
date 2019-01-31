package au.com.autogeneral.swagger.bean;

public class BalanceTestResult {
    String input;
    boolean isBalanced;

    public BalanceTestResult(String input, boolean isBalanced) {
        this.input = input;
        this.isBalanced = isBalanced;
    }

    public BalanceTestResult() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isisBalanced() {
        return isBalanced;
    }

    public void setIsBalanced(boolean balanced) {
        isBalanced = balanced;
    }
}
