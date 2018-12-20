package at.simulevski.couchbaseneo4jdemo.neo4j.persistence;

import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoUser;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeoUserRepository extends Neo4jRepository<NeoUser, Long> {
    @Query("MATCH (i:NeoUser{cbId:{cbId}}) DETACH DELETE i")
    Iterable<Long> deleteByCbId(@Param("cbId") String cbId);

    @Query("MATCH (u:NeoUser{cbId:{cbId}})-[r:OWNS]->(i:NeoItem) RETURN u,r,i")
    NeoUser findByCbId(@Param("cbId") String cbId);
}
