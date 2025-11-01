package com.example.demo.entities.enums;
/*
* A classe enum atribui um valor numérico implícito para cada constante, começando por 0,
* Se algum programar vier a mudar a ordem das variáveis(colocando shipped no lugar de PAID), os valores
* delas também mudarão (PAID virará 2 e SHIPPED 1), o que poderá gerar conflito com os dados salvos no banco.
* Para solucionar, devemos apontar explicitamente o enumerável de cada variável
* */
public enum OrderStatus {
    WAITING_PAIMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order Status Code");
    }
}
