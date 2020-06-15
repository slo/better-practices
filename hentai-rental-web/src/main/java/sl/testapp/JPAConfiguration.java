package sl.testapp;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "sl.testapp")
class JPAConfiguration {

	@Bean
	DataSource getDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setDriverClassName("org.h2.Driver");
		// dataSource.setUrl("jdbc:hsqldb:file:///g:/tools/hsqldb/data/hentaidb/hentaidb");
		//dataSource.setUrl("jdbc:hsqldb:mem:hentaidb");
		dataSource.setUrl("jdbc:h2:mem:test;TRACE_LEVEL_FILE=3");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManager.setPackagesToScan("sl.testapp");

		final Properties jpaProperties = new Properties();
		//jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		jpaProperties.setProperty("hibernate.format_sql", "true");
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		// jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		jpaProperties.setProperty("jadira.usertype.autoRegisterUserTypes", "true");

		entityManager.setJpaProperties(jpaProperties);
		return entityManager;
	}

	@Bean
	PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
