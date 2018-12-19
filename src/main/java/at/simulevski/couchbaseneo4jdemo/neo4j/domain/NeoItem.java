package at.simulevski.couchbaseneo4jdemo.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Relationship(type = "OWNS", direction = Relationship.INCOMING)
    private List<Ownership> ownerships = new ArrayList<>();
}
