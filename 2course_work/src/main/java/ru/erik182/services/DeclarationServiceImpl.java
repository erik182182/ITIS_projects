package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.erik182.models.Declaration;
import ru.erik182.models.User;
import ru.erik182.repositories.DeclarationRepository;

import java.util.Set;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    @Autowired
    private DeclarationRepository declarationRepository;

    @Override
    public Set<Declaration> getDeclarationsOfUser(User user) {
        if(user == null) return null;
        return declarationRepository.findDeclarationsOfUser(user.getId());
    }
}
