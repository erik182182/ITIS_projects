package com.sera.wellness.repositories;

import com.sera.wellness.models.Friend;
import com.sera.wellness.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryEntityManagerImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> findByEmail(String email) {
        Query query = em.createQuery("SELECT u from User u where u.email = :email");
        query.setParameter("email",email);
        try {
            return Optional.ofNullable((User) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void save(User model) {
        em.merge(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.persist(User.builder().id(id).build());
    }

    @Override
    @Transactional
    public void update(User model) {
//        em.persist(model);
        em.merge(model);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.ofNullable(em.find(User.class,id));
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT a FROM User a").getResultList();
    }

    @Override
    public List<Friend> getFriends(Long userId) {
      List<Friend> friends = em.createQuery("SELECT f FROM Friend f WHERE f.user.id = :userId", Friend.class).setParameter("userId", userId).getResultList();
      friends.addAll(em.createQuery("SELECT f FROM Friend f WHERE f.friend.id = :userId", Friend.class).setParameter("userId", userId).getResultList());
      return friends;
    }

    @Override
    public List<User> findAllByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.firstName LIKE :name OR u.lastName LIKE :name", User.class).setParameter("name", name + "%").getResultList();
    }

    @Override
    public void addToFriend(Long userId, Long friendId) {
        Query query = em.createNativeQuery("insert into friends(user_id, friend_id) values (:user_id, :friend_id)");
        query.setParameter("user_id", userId);
        query.setParameter("friend_id", friendId);
        query.executeUpdate();
    }

    @Override
    public Optional<User> getFriend(Long userId, Long friendId) {
        Optional<User> userOptional = Optional.empty();
        List<Friend> friends = em.createQuery("SELECT f FROM Friend f WHERE f.user.id = :userId", Friend.class).setParameter("userId", userId).getResultList();
        friends.addAll(em.createQuery("SELECT f FROM Friend f WHERE f.friend.id = :userId", Friend.class).setParameter("userId", userId).getResultList());

        for (Friend friend: friends) {
            if (friend.getFriend().getId().equals(friendId) || friend.getUser().getId().equals(friendId)){
                userOptional =
                        Optional.of(em.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class).setParameter("id", friendId).getSingleResult());
            }
        }
        return userOptional;
    }
}
