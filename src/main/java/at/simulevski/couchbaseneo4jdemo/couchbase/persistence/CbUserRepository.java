package at.simulevski.couchbaseneo4jdemo.couchbase.persistence;

import at.simulevski.couchbaseneo4jdemo.couchbase.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CbUserRepository extends CrudRepository<User,String> {
    User findByUsername(String username);
}
