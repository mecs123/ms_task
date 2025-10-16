package com.task.api.task001.infrastructure.entryPoints.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgreSQLConnectionPool {

	@Bean
	public ConnectionFactory connectionFactory() {

		String username = "postgres";
		String password = "1234";
		String host = "localhost";
		Long port = 5432L;
		String dbname = "task";
		String schemaValue = "public";
		return new PostgresqlConnectionFactory(
				PostgresqlConnectionConfiguration.builder()
						.host(host)
						.port(5432)
						.database(dbname)
						.username(username)
						.password(password)
						.schema(schemaValue)
						.build()
		);
	}
}
