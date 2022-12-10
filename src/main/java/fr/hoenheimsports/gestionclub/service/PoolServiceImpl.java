package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.model.Competition;
import fr.hoenheimsports.gestionclub.model.Pool;
import fr.hoenheimsports.gestionclub.repository.PoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PoolServiceImpl implements PoolService {

    final private PoolRepository poolRepository;



    @Override
    public Pool createOrUpdate(String num, String name) {
        Optional<Pool> optionalPool = this.poolRepository.findById(num);
        Pool pool = optionalPool.orElseGet(() -> this.poolCreate(num));
        pool.setName(name);

        return this.poolRepository.save(pool);
    }

    private Pool poolCreate(String num) {
        Pool newPool = new Pool();
        newPool.setNum(num);
        return newPool;
    }
}
