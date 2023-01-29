package demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan("demo.*")
@PropertySource("classpath:/config/dbConfig.properties")
public class DataSourceConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(env.getProperty("hostname"));
		dataSource.setPort(Integer.parseInt(env.getProperty("port")));
		dataSource.setUser(env.getProperty("userName"));
		dataSource.setPassword(env.getProperty("password"));
		dataSource.setDatabaseName(env.getProperty("databse"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJDBCTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJDBCTemplate() {
		return new NamedParameterJdbcTemplate(getDataSource());
	}

}
