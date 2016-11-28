package com.autodesk.commonlog.repo;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.stereotype.Component;

import com.autodesk.commonlog.domain.AuditLog;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;

@Component("auditLogRepo")
public class AuditLogRepoImpl implements AuditLogRepo {
  private static final Logger logger = LoggerFactory.getLogger(AuditLogRepoImpl.class);
  @Autowired
  private CassandraTemplate cassandraTemplate;

  @Autowired
  private Session session;

  @Autowired
  private CassandraConverter cassandraConverter;


  private static final int MAX_FETCH_SIZE = 1000;


  @Override
  public List<AuditLog> getLogByItem(String tenant, String itemId) throws NotFoundException {
    Select select = QueryBuilder.select().from(AuditLog.class.getAnnotation(Table.class).value());
    select.where(QueryBuilder.eq("tenant", tenant));
    select.where(
        QueryBuilder.eq("item_id", itemId));
    return cassandraTemplate.select(select, AuditLog.class);
  }

  public AuditLog saveAuditLog(AuditLog auditLog) {
    auditLog.setLogId(UUIDs.timeBased());
     Insert insert = CassandraTemplate.createInsertQuery(
        AuditLog.class.getAnnotation(Table.class).value(), auditLog, null, cassandraConverter);
         cassandraTemplate.execute(insert);
     return auditLog;
  }

}


