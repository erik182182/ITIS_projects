package ru.erik182.repositories;

import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;

import java.util.List;

public interface DeclarationRepository extends CrudRepository<Declaration> {
    List<Declaration> getDeclarationsOfDirectionById(Long id);
    List<Declaration> getDeclarationsOfUser(String passport);
}
