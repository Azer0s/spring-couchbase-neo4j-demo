package at.simulevski.couchbaseneo4jdemo.couchbase.persistence;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CbItemRepository extends CrudRepository<Item,String> {
}
