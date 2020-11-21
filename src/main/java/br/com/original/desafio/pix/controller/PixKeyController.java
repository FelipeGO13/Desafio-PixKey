package br.com.original.desafio.pix.controller;

import br.com.original.desafio.pix.dto.CreatePixKeyRequest;
import br.com.original.desafio.pix.dto.PixKeyResponse;
import br.com.original.desafio.pix.dto.UpdatePixKeyRequest;
import br.com.original.desafio.pix.dto.mapper.PixKeyMapper;
import br.com.original.desafio.pix.model.PixKey;
import br.com.original.desafio.pix.service.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chave")
public class PixKeyController {

    @Autowired
    private PixKeyService pixKeyService;

    @Autowired
    private PixKeyMapper pixKeyMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PixKeyResponse create(@Valid @RequestBody CreatePixKeyRequest createPixKeyRequest){
        PixKey pixKey = pixKeyMapper.toPixKey(createPixKeyRequest);
        return pixKeyMapper.toPixKeyResponse(pixKeyService.create(pixKey));
    }

    @GetMapping("/{customerId}")
    public Iterable<PixKeyResponse> getById(@PathVariable Long customerId){
        return pixKeyMapper.toPixKeyResponseList(pixKeyService.getByCustomerId(customerId));
    }

    @DeleteMapping("/{customerId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long customerId, @PathVariable Long id){
        pixKeyService.delete(customerId, id);
    }

    @PutMapping("/{customerId}/{id}")
    public PixKeyResponse updateById(@PathVariable Long customerId, @PathVariable Long id, @Valid @RequestBody UpdatePixKeyRequest updatePixKeyRequest){
        PixKey pixKey = pixKeyMapper.toPixKey(updatePixKeyRequest);
        return pixKeyMapper.toPixKeyResponse(pixKeyService.update(customerId, id, pixKey));
    }

}
