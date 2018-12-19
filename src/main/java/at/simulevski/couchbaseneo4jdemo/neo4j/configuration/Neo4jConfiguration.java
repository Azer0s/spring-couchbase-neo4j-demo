package at.simulevski.couchbaseneo4jdemo.neo4j.configuration;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories("at.simulevski.couchbaseneo4jdemo.neo4j.persistence")
@EnableTransactionManagement
public class Neo4jConfiguration {

    @Value("${neo4j.host}")
    private String host;

    @Value("${neo4j.username}")
    private String user;

    @Value("${neo4j.password}")
    private String password;

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "at.simulevski.couchbaseneo4jdemo.neo4j.domain");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(host)
                .credentials(user, password)
                .build();
        return configuration;
    }
}
