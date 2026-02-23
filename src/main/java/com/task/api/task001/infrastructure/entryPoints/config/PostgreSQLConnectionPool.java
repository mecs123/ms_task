package com.task.api.task001.infrastructure.entryPoints.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgreSQLConnectionPool {

	@Value("${PGHOST:localhost}")
	private String host;

	@Value("${PGPORT:5432}")
	private int port;

	@Value("${PGDATABASE:mi_base}")
	private String database;

	@Value("${PGUSER:postgres}")
	private String username;

	@Value("${PGPASSWORD:password}")
	private String password;

	@Value("${POSTGRES_SCHEMA:public}")
	private String schema;

	/*
	@Bean
	public ConnectionFactory connectionFactory() {
		PostgresqlConnectionConfiguration config = PostgresqlConnectionConfiguration.builder()
				.host(host)
				.port(port)
				.database(database)
				.username(username)
				.password(password)
				.schema(schema)
				.enableSsl()
				.sslMode(SSLMode.REQUIRE)
				.build();

		return new PostgresqlConnectionFactory(config);
	}

	 */
}