package pro.sky.skyprospringcalculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculator")
    public String welcome() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/calculator/plus")
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return buildView(a, b, calculatorService.plus(a, b), "+");
    }

    @GetMapping("/calculator/minus")
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                        @RequestParam(value = "num2", required = false) Integer b) {
        return buildView(a, b, calculatorService.minus(a, b), "-");
    }

    @GetMapping("/calculator/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                           @RequestParam(value = "num2", required = false) Integer b) {
        return buildView(a, b, calculatorService.multiply(a, b), "*");
    }

    @GetMapping("/calculator/divide")
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        return buildView(a, b, calculatorService.divide(a, b), "/");
    }

    private String buildView(Integer a,
                             Integer b,
                             Number result,
                             String operation) {
        if (a == null || b == null) {
            return "не передан один из параметров";
        }
        if ("/".equals(operation) && b == 0) {
            return "На ноль делить нельзя";
        }
        return a + " " + operation + " " + b + " = " + result;
    }
}


