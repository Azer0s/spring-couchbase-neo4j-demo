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

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private CbUserRepository cbUserRepository;

    @Autowired
    private CbItemRepository cbItemRepository;

    @Autowired
    private NeoUserRepository neoUserRepository;

    @Autowired
    private NeoItemRepository neoItemRepository;

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

    public CbUser getUser(String name) {
        var user = cbUserRepository.findByUsername(name);
        if (user == null){
            return null;
        }

        var neoUser = neoUserRepository.findByCbId(user.getId());
        //var neoItems = neoItemRepository.getItemsByCbId(neoUser.getCbId());

        var items = new ArrayList<CbItem>();
        for (var neoItem : neoUser.getItems()) {
            items.add(cbItemRepository.findById(neoItem.getCbId()).orElse(null));
        }

        user.setItems(items);

        return user;
    }

    public boolean deleteUser(String name) {
        var user = cbUserRepository.findByUsername(name);
        if (user == null){
            return false;
        }

        cbUserRepository.deleteById(user.getId());
        neoUserRepository.deleteByCbId(user.getId());

        return true;
    }

    public boolean createItemLink(String userName, String itemName) {
        var user = cbUserRepository.findByUsername(userName);
        var item = cbItemRepository.findByName(itemName);
        if (user == null || item == null){
            return false;
        }

        var neoUser = neoUserRepository.findByCbId(user.getId());
        var neoItem = neoItemRepository.findByCbId(item.getId());

        neoUser.addItem(neoItem);
        neoUserRepository.save(neoUser);

        return true;
    }

    public boolean deleteItemLink(String userId, String itemId) {
        return false;
    }
}
