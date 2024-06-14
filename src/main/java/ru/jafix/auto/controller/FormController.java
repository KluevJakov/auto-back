package ru.jafix.auto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jafix.auto.entity.FormRequestDTO;
import ru.jafix.auto.entity.Order;
import ru.jafix.auto.entity.User;
import ru.jafix.auto.repository.OrderRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order/")
public class FormController {

    @Autowired
    protected OrderRepository orderRepository;

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> submitForm(@RequestBody String body) throws UnsupportedEncodingException {
        Map<String, String> params = parseBody(body);

        FormRequestDTO formRequestDTO = new FormRequestDTO();
        formRequestDTO.setTildaSpecFormName(params.get("tildaspec-formname"));
        formRequestDTO.setName(params.get("Name"));
        formRequestDTO.setPhone(params.get("Phone"));
        formRequestDTO.setFrom(params.get("Откуда"));
        formRequestDTO.setTo(params.get("Куда"));
        formRequestDTO.setCargoWeight(params.get("Вес груза"));
        formRequestDTO.setCargoVolume(params.get("Объем груза"));

        FormRequestDTO.TildaPayment tildapayment;
        if (params.containsKey("tildapayment")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                tildapayment = objectMapper.readValue(params.get("tildapayment"), FormRequestDTO.TildaPayment.class);
                formRequestDTO.setTildapayment(tildapayment);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Invalid tildapayment format");
            }
        }

        System.out.println("Form Name: " + formRequestDTO.getTildaSpecFormName());
        System.out.println("Name: " + formRequestDTO.getName());
        System.out.println("Phone: " + formRequestDTO.getPhone());
        System.out.println("From: " + formRequestDTO.getFrom());
        System.out.println("To: " + formRequestDTO.getTo());
        System.out.println("Cargo Weight: " + formRequestDTO.getCargoWeight());
        System.out.println("Cargo Volume: " + formRequestDTO.getCargoVolume());

        Order order = new Order();
        order.setName(formRequestDTO.getName());
        order.setPhone(formRequestDTO.getPhone());
        order.setFrom(formRequestDTO.getFrom());
        order.setTo(formRequestDTO.getTo());
        order.setCargoWeight(formRequestDTO.getCargoWeight());
        order.setCargoVolume(formRequestDTO.getCargoVolume());
        if (formRequestDTO.getTildapayment() != null) {
            String prods = "";
            for (FormRequestDTO.TildaPayment.Product product : formRequestDTO.getTildapayment().getProducts()) {
                prods += product.getName() + ", ";
            }
            order.setProducts(prods);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        order.setUser(user);
        orderRepository.save(order);

        return ResponseEntity.ok("Form submitted successfully");
    }

    private Map<String, String> parseBody(String body) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] parts = pair.split("=", 2);
            String key = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
            String value = parts.length > 1 ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8) : "";
            params.put(key, value);
        }

        return params;
    }
}
