package demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@ComponentScan("demo.*")
@PropertySource("classpath:/config/dbConfig.properties")
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(environment.getProperty("hostname"));
		dataSource.setPort(Integer.parseInt(environment.getProperty("port")));
		dataSource.setUser(environment.getProperty("userName"));
		dataSource.setPassword(environment.getProperty("password"));
		dataSource.setDatabaseName(environment.getProperty("databse"));
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
	
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[] {
            "demo.model"
        });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

	@Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
	

}
