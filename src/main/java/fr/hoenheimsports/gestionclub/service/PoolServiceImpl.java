package fr.hoenheimsports.gestionclub.service;

import fr.hoenheimsports.gestionclub.entity.Competition;
import fr.hoenheimsports.gestionclub.entity.Pool;
import fr.hoenheimsports.gestionclub.repository.PoolRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PoolServiceImpl implements PoolService {

    final private PoolRepository poolRepository;



    @Override
    public Pool createOrUpdate(String num, String name, Competition c) {
        Optional<Pool> optionalPool = this.poolRepository.findById(num);
        Pool pool = optionalPool.orElseGet(() -> this.poolCreate(num));
        pool.setName(name);
        pool.setCompetition(c);
        return this.poolRepository.save(pool);
    }

    private Pool poolCreate(String num) {
        Pool newPool = new Pool();
        newPool.setNum(num);
        return newPool;
    }
}
