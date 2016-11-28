package com.autodesk.commonlog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.support.CassandraExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// import com.autodesk.microservice.commons.service.CassandraDbSyncService;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;

/**
 * Support for Cassandra
 *
 * @author Ashwin Jeksani
 */
@Configuration
@Component
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

  

  @Value("${CASSANDRA_CONTACTPOINTS}")
  private String contactpoints;

  @Value("${CASSANDRA_PORT}")
  private String port;

  @Value("${CASSANDRA_KEYSPACE}")
  private String keyspace;
  
  // @Autowired
  // CassandraDbSyncService cassandraDbSyncService;


  @Bean
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean factoryBean = new InternalCassandraClusterFactoryBean();
    factoryBean.setContactPoints(contactpoints);
    factoryBean.setPort(Integer.parseInt(port));
    return factoryBean;
  }

  @Override
  protected String getKeyspaceName() {
    return keyspace;
  }

  @Bean
  public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
    return new BasicCassandraMappingContext();
  }


  /**
   * This is basically an unfortunate hack. We would like to default our consistency level for all
   * reads and writes to LOCAL_QUORUM but spring data doesn't provide that yet.
   *
   * For more information see https://jira.spring.io/browse/DATACASS-204 Until that JIRA is
   * addressed we would need to add any bootstrapping of cluster bean in this class
   */
  class InternalCassandraClusterFactoryBean extends CassandraClusterFactoryBean {

    private Cluster cluster;

    private final PersistenceExceptionTranslator
        exceptionTranslator =
        new CassandraExceptionTranslator();


    /*
     * Attributes needed for cluster builder
     */
    private String contactPoints;
    private int port;
    private QueryOptions
        options =
        new QueryOptions().setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);

    @Override
    public Cluster getObject() throws Exception {
      return cluster;
    }

    @Override
    public Class<? extends Cluster> getObjectType() {
      return Cluster.class;
    }

    @Override
    public boolean isSingleton() {
      return true;
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
      return exceptionTranslator.translateExceptionIfPossible(ex);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
      if (!StringUtils.hasText(contactPoints)) {
        throw new IllegalArgumentException("at least one server is required");
      }

      Cluster.Builder builder = Cluster.builder();

      builder.addContactPoints(StringUtils.commaDelimitedListToStringArray(contactPoints))
          .withPort(port);
      //default all reads and writes to
      builder.withQueryOptions(options);

      cluster = builder.build();
      
      //Create the keyspace if required and execute the CQL files
      // cassandraDbSyncService.runCassandraSetup();
    }


    @Override
    public void destroy() throws Exception {
      cluster.close();
    }

    /**
     * Sets a comma-delimited string of the contact points (hosts) to connect to.
     */
    public void setContactPoints(String contactPoints) {
      this.contactPoints = contactPoints;
    }

    public void setPort(int port) {
      this.port = port;
    }


  }

}
