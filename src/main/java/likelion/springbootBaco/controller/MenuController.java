package likelion.springbootBaco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import likelion.springbootBaco.service.MenuService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MenuController {
//    private final MenuService menuService;
//    @PostMapping("order/chicken")
//    public String orderChicken(@RequestBody OrderSheet orderSheet) {
//        Long menuNumber = orderSheet.getMenuNumber();
//        String size = orderSheet.getSize();
//        List<String> toppings = orderSheet.getToppings();
//        menuService.orderChicken(menuNumber, size, toppings);
//        return "";
//    }

    @PostMapping("/api/echo/json")
    @ResponseBody
    public JsonRequest jsonEcho(@RequestBody JsonRequest request) {
        return request;
    }

    @PostMapping("/api/echo/person")
    @ResponseBody
    public Person personEcho(@RequestBody Person person) {
        return person;
    }

    @Data
    static class JsonRequest {
        private String context;
    }

    @Data
    static class Person {
        private String firstName;
        private String lastName;
        private String employeeId;
        private String designation;
        private List<String> languageExpertise;
        private String car;
    }


}
