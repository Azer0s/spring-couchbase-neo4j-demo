package at.simulevski.couchbaseneo4jdemo.service;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbItem;
import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbUser;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbItemRepository;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbUserRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoUser;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoItemRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
@Service
public class UserService {

    @Autowired
    private CbUserRepository cbUserRepository;

    @Autowired
    private CbItemRepository cbItemRepository;

    @Autowired
    private NeoUserRepository neoUserRepository;

    @Transactional
    public boolean createUser(String username) {
        if (cbUserRepository.findByUsername(username) != null){
            return false;
        }

        var user = CbUser.builder()
                .username(username)
                .build();
        cbUserRepository.save(user);

        var neoUser = NeoUser.builder().cbId(user.getId()).build();
        neoUserRepository.save(neoUser);
        return true;
    }

    @Transactional(readOnly = true)
    public CbUser getUser(String name) {
        var user = cbUserRepository.findByUsername(name);
        if (user == null){
            return null;
        }

        var neoUser = neoUserRepository.findByCbId(user.getId());
        var items = new ArrayList<CbItem>();
        for (var neoItem : neoUser.getItems()) {
            items.add(cbItemRepository.findById(neoItem.getCbId()).orElse(null));
        }

        user.setItems(items);

        return user;
    }

    @Transactional
    public boolean deleteUser(String name) {
        var user = cbUserRepository.findByUsername(name);
        if (user == null){
            return false;
        }

        cbUserRepository.deleteById(user.getId());
        neoUserRepository.deleteByCbId(user.getId());

        return true;
    }
}
