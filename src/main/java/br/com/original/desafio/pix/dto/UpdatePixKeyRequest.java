package br.com.original.desafio.pix.dto;

import br.com.original.desafio.pix.model.KeyType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UpdatePixKeyRequest {

    @JsonProperty("tipo_chave")
    private KeyType keyType;

    @NotNull
    @JsonProperty("chave")
    private String key;

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
