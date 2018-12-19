package at.simulevski.couchbaseneo4jdemo.couchbase.persistence;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.CbUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CbUserRepository extends CrudRepository<CbUser,String> {
    CbUser findByUsername(String username);
}
