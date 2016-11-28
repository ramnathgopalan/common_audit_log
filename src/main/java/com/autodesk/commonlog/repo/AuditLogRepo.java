package com.autodesk.commonlog.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.autodesk.commonlog.domain.AuditLog;

@Repository
public interface AuditLogRepo {
  // extends CrudRepository<AuditLog, AuditLogKey> {
  public List<AuditLog> getLogByItem(String tenant, String itemId);
}

