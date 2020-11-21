package br.com.original.desafio.pix.dto.mapper;

import br.com.original.desafio.pix.dto.CreatePixKeyRequest;
import br.com.original.desafio.pix.dto.PixKeyResponse;
import br.com.original.desafio.pix.dto.UpdatePixKeyRequest;
import br.com.original.desafio.pix.model.PixKey;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PixKeyMapper {

    public PixKey toPixKey(CreatePixKeyRequest createPixKeyRequest){
        PixKey pixKey = new PixKey();

        pixKey.setCustomerId(createPixKeyRequest.getCustomerId());
        pixKey.setKey(createPixKeyRequest.getKey());
        pixKey.setType(createPixKeyRequest.getKeyType());
        pixKey.setCreationDate(LocalDate.now());

        return pixKey;

    }

    public PixKey toPixKey(UpdatePixKeyRequest updatePixKeyRequest ){
        PixKey pixKey = new PixKey();

        pixKey.setKey(updatePixKeyRequest.getKey());
        pixKey.setType(updatePixKeyRequest.getKeyType());

        return pixKey;

    }

    public PixKeyResponse toPixKeyResponse(PixKey pixKey){
        PixKeyResponse pixKeyResponse = new PixKeyResponse();

        pixKeyResponse.setCustomerId(pixKey.getCustomerId());
        pixKeyResponse.setId(pixKey.getId());
        pixKeyResponse.setKey(pixKey.getKey());
        pixKeyResponse.setKeyType(pixKey.getType());
        pixKeyResponse.setCreationDate(pixKey.getCreationDate());

        return pixKeyResponse;
    }

    public Iterable<PixKeyResponse> toPixKeyResponseList(Iterable<PixKey> pixKeyList){
        List<PixKeyResponse> pixKeyResponseList = new ArrayList<>();

        for(PixKey pixKey: pixKeyList){
            PixKeyResponse pixKeyResponse = new PixKeyResponse();

            pixKeyResponse.setCustomerId(pixKey.getCustomerId());
            pixKeyResponse.setId(pixKey.getId());
            pixKeyResponse.setKey(pixKey.getKey());
            pixKeyResponse.setKeyType(pixKey.getType());
            pixKeyResponse.setCreationDate(pixKey.getCreationDate());

            pixKeyResponseList.add(pixKeyResponse);
        }


        return pixKeyResponseList;
    }


}
