package at.simulevski.couchbaseneo4jdemo.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Data
@RelationshipEntity(type = "OWNS")
class Ownership {
    @Id
    @GeneratedValue
    Long id;

    @StartNode
    private NeoUser user;

    @EndNode
    private NeoItem item;
}
