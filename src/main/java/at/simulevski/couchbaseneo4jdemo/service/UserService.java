package at.simulevski.couchbaseneo4jdemo.service;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.User;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CbUserRepository cbUserRepository;

    public boolean createUser(String username) {
        if (cbUserRepository.findByUsername(username) != null){
            return false;
        }

        var user = User.builder()
                .username(username)
                .build();
        cbUserRepository.save(user);
        return true;
    }

    public User getUser(String name) {
        return cbUserRepository.findByUsername(name);
    }

    public boolean deleteUser(String name) {
        return false;
    }

    public boolean createItemLink(String userId, String itemId) {
        return false;
    }

    public boolean deleteItemLink(String userId, String itemId) {
        return false;
    }
}
