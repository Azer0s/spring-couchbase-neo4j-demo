package at.simulevski.couchbaseneo4jdemo.service;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    public boolean createItem(String name, String description) {
        return false;
    }

    public Item getItem(String id) {
        return null;
    }

    public boolean deleteItem(String id) {
        return false;
    }
}
