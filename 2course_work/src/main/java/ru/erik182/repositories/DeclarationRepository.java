package ru.erik182.repositories;

import ru.erik182.models.Declaration;

import java.util.List;
import java.util.Set;

public interface DeclarationRepository extends CrudRepository<Declaration> {
    Set<Declaration> findDeclarationsOfUser(Long userId);
}
