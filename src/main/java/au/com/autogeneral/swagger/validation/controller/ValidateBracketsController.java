package au.com.autogeneral.swagger.validation.controller;

import au.com.autogeneral.swagger.bean.ErrorResult;
import au.com.autogeneral.swagger.bean.ErrorDetail;
import au.com.autogeneral.swagger.bean.IValidationResult;
import au.com.autogeneral.swagger.bean.BalanceTestResult;
import au.com.autogeneral.swagger.validation.model.ValidationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateBracketsController {
    static final int MAX_INPUT_TEXT_LEN = 100;

    @RequestMapping(value = "/tasks/validateBrackets", method = RequestMethod.GET)
    public IValidationResult validateBrackets(@RequestParam(name = "input") String input) {
        if (StringUtils.isEmpty(input) || input.length() > MAX_INPUT_TEXT_LEN )
        {
            return new ErrorResult(new ErrorDetail[] {
                    new ErrorDetail("params", "text", "Must be between 1 and 50 chars long", input)
            }, "ValidationError");
        }
        else {
            boolean isBalanced = ValidationUtils.isBalanced(input);
            return new BalanceTestResult(input, isBalanced);
        }
    }

}
