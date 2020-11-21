package br.com.original.desafio.pix.service;

import br.com.original.desafio.pix.model.PixKey;
import br.com.original.desafio.pix.repository.PixKeyRepository;
import exception.PixKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PixKeyService {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    public PixKey create(PixKey pixKey){
        return pixKeyRepository.save(pixKey);
    }

    public Iterable<PixKey> getByCustomerId(Long customerId){
        Iterable<PixKey> keys = pixKeyRepository.findByCustomerId(customerId);

        if(StreamSupport.stream(keys.spliterator(), false).count() == 0) {
            throw new PixKeyException();
        }

        return keys;
    }

    public void delete(Long customerId, Long id){
        Optional<PixKey> deletedKey =  pixKeyRepository.findByCustomerIdAndId(customerId, id);

        if(!deletedKey.isPresent()){
            throw new PixKeyException();
        }

        pixKeyRepository.delete(deletedKey.get());
    }

    public PixKey update(Long customerId, Long id,PixKey pixKey){
        Optional<PixKey> key = pixKeyRepository.findByCustomerIdAndId(customerId, id);


        if(!key.isPresent()){
            throw new PixKeyException();
        }

        key.get().setType(pixKey.getType());
        key.get().setKey(pixKey.getKey());

        return pixKeyRepository.save(key.get());
    }


}
