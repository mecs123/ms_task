package com.task.api.task001.infrastructure.entryPoints.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgreSQLConnectionPool {

	private final PostgresqlConnectionProperties properties;

    public PostgreSQLConnectionPool(PostgresqlConnectionProperties properties) {
        this.properties = properties;
    }

    @Bean
	public ConnectionFactory connectionFactory() {

//		String username = "postgres";
//		String password = "1234";
//		String host = "localhost";
//		String dbname = "task";
//		String schemaValue = "public";

//		String username = "postgres";
//		String password = "Manolo9315308+1";
//		String host = "app-task-db.postgres.database.azure.com";
//		String dbname = "task";
//		String schemaValue = "public";
		return new PostgresqlConnectionFactory(
				PostgresqlConnectionConfiguration.builder()
						.host(properties.getHost())
						.port(properties.getPort())
						.database(properties.getDatabase())
						.username(properties.getUsername())
						.password(properties.getPassword())
						.schema(properties.getSchema())
						.build()
		);
	}
}
