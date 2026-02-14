package tr.kontas.gql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class DirectiveConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(AuthDirective authDirective) {
        return builder ->
                builder.directive("auth", authDirective);
    }
}