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
    private TildaPayment tildapayment;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TildaPayment {
        private String currency;
        private int amount;
        private String system;
        private int timezoneoffset;
        private Product[] products;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Product {
            private String name;
            private int price;
            private String img;
            private String recid;
            private String lid;
            private String pack_label;
            private String pack_m;
            private String pack_x;
            private String pack_y;
            private String pack_z;
            private String[] part_uids;
            private String gen_uid;
            private String url;
            private int quantity;
            private int amount;
            private long ts;
        }
    }
}
