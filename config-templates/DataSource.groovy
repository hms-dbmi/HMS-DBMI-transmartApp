 /**
 * Configuration for database connection - this file will be loaded 
 * by the tranSMART application when the tomcat is restarted
 */

dataSource {
	pooled = true
	driverClassName ="oracle.jdbc.driver.OracleDriver"
	url = "jdbc:oracle:thin:@localhost:1521:ORCL"
	
	username = "biomart_user"
	password = "BIOMART_USER"
	
	dialect = "org.hibernate.dialect.Oracle10gDialect"
	
	loggingSql =true 
}

hibernate {
	cache.use_second_level_cache=true
	cache.use_query_cache=true
	cache.provider_class='org.hibernate.cache.EhCacheProvider'
	connection.pool_size=30
}

