package at.simulevski.couchbaseneo4jdemo.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@NodeEntity
public class NeoItem {
    @Id
    @GeneratedValue
    Long id;

    @Index(unique = true)
    private String cbId;
}
