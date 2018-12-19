package at.simulevski.couchbaseneo4jdemo.neo4j.persistence;

import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoUser;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeoUserRepository extends Neo4jRepository<NeoUser, Long> {
    void deleteByCbId(String cbId);
    NeoUser findByCbId(String cbId);
    @Query(value = "MATCH (u:NeoUser)-[:OWNS]->(i:NeoItem) WHERE u.cbId = {0} RETURN i")
    List<NeoUser> getUsersByCbId(String id);
}
