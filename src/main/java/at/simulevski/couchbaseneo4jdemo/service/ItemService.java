package at.simulevski.couchbaseneo4jdemo.service;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbItem;
import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbUser;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbItemRepository;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbUserRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoItem;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoItemRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
@Service
public class ItemService {

    @Autowired
    private CbUserRepository cbUserRepository;

    @Autowired
    private CbItemRepository cbItemRepository;

    @Autowired
    private NeoUserRepository neoUserRepository;

    @Autowired
    private NeoItemRepository neoItemRepository;

    public boolean createItem(String name, String description) {
        if (cbItemRepository.findByName(name) != null){
            return false;
        }

        var item = CbItem.builder()
                .name(name)
                .description(description)
                .build();

        cbItemRepository.save(item);

        var neoItem = NeoItem.builder().cbId(item.getId()).build();
        neoItemRepository.save(neoItem);
        return true;
    }

    public CbItem getItem(String name) {
        var item = cbItemRepository.findByName(name);
        if (item == null){
            return null;
        }

        var neoUsers = neoUserRepository.getUsersByCbId(item.getId());
        var users = new ArrayList<CbUser>();
        for (var neoUser : neoUsers) {
            users.add(cbUserRepository.findById(neoUser.getCbId()).orElse(null));
        }

        item.setUsers(users);
        return item;
    }

    public boolean deleteItem(String name) {
        var item = cbItemRepository.findByName(name);
        if (item == null){
            return false;
        }

        cbItemRepository.deleteById(item.getId());
        neoItemRepository.deleteByCbId(item.getId());

        return true;
    }
}
