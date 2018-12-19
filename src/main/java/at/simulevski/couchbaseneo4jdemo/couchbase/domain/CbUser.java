package at.simulevski.couchbaseneo4jdemo.couchbase.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document
@Data
public class CbUser {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @NotNull
    @Field
    private String username;

    private List<CbItem> items = new ArrayList<>();
}