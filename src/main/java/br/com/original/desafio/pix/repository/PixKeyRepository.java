package br.com.original.desafio.pix.repository;

import br.com.original.desafio.pix.model.PixKey;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PixKeyRepository extends CrudRepository<PixKey, Long> {

    Iterable<PixKey> findByCustomerId(Long customerId);

    @Transactional
    Optional<PixKey> deleteByCustomerIdAndId(Long customerId, Long id);

    Optional<PixKey> findByCustomerIdAndId(Long customerId, Long id);

}
