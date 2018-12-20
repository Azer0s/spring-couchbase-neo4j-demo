package at.simulevski.couchbaseneo4jdemo.service;

import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbItemRepository;
import at.simulevski.couchbaseneo4jdemo.couchbase.persistence.CbUserRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoItemRepository;
import at.simulevski.couchbaseneo4jdemo.neo4j.persistence.NeoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("Duplicates")
@Service
public class LinkService {

    @Autowired
    private CbUserRepository cbUserRepository;

    @Autowired
    private CbItemRepository cbItemRepository;

    @Autowired
    private NeoUserRepository neoUserRepository;

    @Autowired
    private NeoItemRepository neoItemRepository;

    @Transactional
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

    @Transactional
    public boolean deleteItemLink(String userName, String itemName) {
        var user = cbUserRepository.findByUsername(userName);
        var item = cbItemRepository.findByName(itemName);
        if (user == null || item == null){
            return false;
        }

        var neoUser = neoUserRepository.findByCbId(user.getId());
        var neoItem = neoItemRepository.findByCbId(item.getId());

        neoUser.removeItem(neoItem);
        neoUserRepository.save(neoUser);

        return false;
    }
}
