package at.simulevski.couchbaseneo4jdemo.neo4j.persistence;

import at.simulevski.couchbaseneo4jdemo.neo4j.domain.NeoItem;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NeoItemRepository extends CrudRepository<NeoItem, Long> {

    void deleteByCbId(String cbId);
    NeoItem findByCbId(String id);

    @Query("MATCH (u:NeoUser{cbId:{userId}})-[:OWNS]->(i:NeoItem) RETURN i")
    List<NeoItem> getItemsByCbId(@Param("userId") String id);
}
