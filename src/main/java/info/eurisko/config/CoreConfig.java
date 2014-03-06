package info.eurisko.config;

import info.eurisko.core.domain.Newsletter;
import info.eurisko.core.repository.NewslettersPersistentRepository;
import info.eurisko.core.repository.NewslettersRepository;
import info.eurisko.core.services.NewsletterEventHandler;
import info.eurisko.core.services.NewsletterService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.yummynoodlebar", excludeFilters = { @ComponentScan.Filter(Configuration.class) })
@PropertySource("classpath:/META-INF/spring/database.properties")
@EnableTransactionManagement
public class CoreConfig {

	@Bean
	public NewsletterService createService(final NewslettersRepository repo) {
		return new NewsletterEventHandler(repo);
	}

	@Bean
	public NewslettersRepository createRepo() {
		// return new NewslettersMemoryRepository();
		return new NewslettersPersistentRepository();
	}

	private static final Logger logger = LoggerFactory.getLogger(CoreConfig.class);

	@Value("#{ environment['database.driverClassName']?:'' }")
	private String dbDriverClass;

	@Value("#{ environment['database.url']?:'' }")
	private String dbUrl;

	@Value("#{ environment['database.vendor']?:'' }")
	private String dbVendor;

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private Environment environment;

	@Bean(name="dataSource")
	public DataSource dataSource() {
		final BasicDataSource bean = new BasicDataSource();

		bean.setDriverClassName(dbDriverClass);
		bean.setUrl(dbUrl);

		bean.setTestOnBorrow(true);
		bean.setTestOnReturn(true);
		bean.setTestWhileIdle(true);
		bean.setNumTestsPerEvictionRun(3);

		bean.setTimeBetweenEvictionRunsMillis(1800000);
		bean.setMinEvictableIdleTimeMillis(1800000);

		bean.setValidationQuery("SELECT version();");

		return bean;
	}

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());

		// No need for persistence.xml - thanks to packagesToScan
		logger.warn("Scanning Package '{}' for entities", Newsletter.class.getPackage().getName());
		bean.setPackagesToScan(Newsletter.class.getPackage().getName());

		final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.valueOf(dbVendor));
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);

		bean.setJpaVendorAdapter(jpaVendorAdapter);

		return bean;
	}

	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager bean = new JpaTransactionManager();

		bean.setEntityManagerFactory(entityManagerFactory().getObject());
		bean.setDataSource(dataSource());
		bean.afterPropertiesSet();
		
		return bean;
	}

	@Bean(name="entityManager")
	public EntityManager entityManager() {
		if (entityManagerFactory() == null)
			logger.error("CEMF IS NULL");

		final EntityManagerFactory entityManagerFactory = entityManagerFactory().getObject();
		if (entityManagerFactory == null) {
			logger.error("EMF IS NULL");
			return null;
		} else {
			try {
				return entityManagerFactory.createEntityManager();
			}
			catch (Exception e) {
				logger.error("EM IS NULL: " + e.getLocalizedMessage());
				return null;
			}
		}
	}
}
