package at.simulevski.couchbaseneo4jdemo.neo4j.persistence;

import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoItem;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeoItemRepository extends Neo4jRepository<NeoItem, Long> {
    @Query("MATCH (i:NeoItem{cbId:{cbId}}) DETACH DELETE i")
    Iterable<Long> deleteByCbId(@Param("cbId") String cbId);
    NeoItem findByCbId(String id);
}
