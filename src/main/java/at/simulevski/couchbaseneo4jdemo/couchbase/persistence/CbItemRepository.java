package at.simulevski.couchbaseneo4jdemo.couchbase.persistence;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CbItemRepository extends CrudRepository<CbItem,String> {
    CbItem findByName(String name);
}
