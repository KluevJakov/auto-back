package ru.jafix.auto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jafix.auto.entity.FormRequestDTO;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order/")
public class FormController {

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

        // Обработка полученных данных
        System.out.println("Form Name: " + formRequestDTO.getTildaSpecFormName());
        System.out.println("Name: " + formRequestDTO.getName());
        System.out.println("Phone: " + formRequestDTO.getPhone());
        System.out.println("From: " + formRequestDTO.getFrom());
        System.out.println("To: " + formRequestDTO.getTo());
        System.out.println("Cargo Weight: " + formRequestDTO.getCargoWeight());
        System.out.println("Cargo Volume: " + formRequestDTO.getCargoVolume());

        // Логика обработки данных формы
        // Например, сохранение в базу данных

        return ResponseEntity.ok("Form submitted successfully");
    }

    private Map<String, String> parseBody(String body) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] parts = pair.split("=", 2); // Split into two parts: key and value
            String key = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);
            String value = parts.length > 1 ? URLDecoder.decode(parts[1], StandardCharsets.UTF_8) : "";
            params.put(key, value);
        }

        return params;
    }
}
