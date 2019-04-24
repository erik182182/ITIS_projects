package ru.erik182.repositories;

import org.springframework.stereotype.Repository;
import ru.erik182.models.Declaration;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class DeclarationRepositoryImpl implements DeclarationRepository {
    @Override
    public Set<Declaration> findDeclarationsOfUser(Long userId) {
        return null;
    }

    @Override
    public Optional<Declaration> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Declaration model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long id, Declaration model) {

    }

    @Override
    public List<Declaration> findAll() {
        return null;
    }
}
