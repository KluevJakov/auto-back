package ru.jafix.auto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormRequestDTO {
    @JsonProperty("tildaspec-formname")
    private String tildaSpecFormName;

    private String Name;
    private String Phone;

    @JsonProperty("Откуда")
    private String from;

    @JsonProperty("Куда")
    private String to;

    @JsonProperty("Вес груза")
    private String cargoWeight;

    @JsonProperty("Объем груза")
    private String cargoVolume;
}
