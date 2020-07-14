package br.com.globallabs.springwebmvc.service;

import br.com.globallabs.springwebmvc.exception.JediNotFoundException;
import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(Long id) {
        final Optional<Jedi> jedi = repository.findById(id);

        if(jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi save(Jedi jedi) {
        return repository.save(jedi);
    }

    public void delete(Long id) {
        Jedi jedi = findById(id);
        repository.delete(jedi);
    }

    public Jedi update(Long id, Jedi jedi) {
        final Optional<Jedi> jediEntity = repository.findById(id);

        if(jediEntity.isPresent()) {
            jediEntity.get().setName(jedi.getName());
            jediEntity.get().setLastName(jedi.getLastName());
            return repository.save(jediEntity.get());
        } else {
            throw new JediNotFoundException();
        }
    }
}
