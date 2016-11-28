package com.autodesk.commonlog.repo;

import java.util.List;

import com.autodesk.commonlog.domain.AuditLog;

public interface AuditLogRepo {
  // extends CrudRepository<AuditLog, AuditLogKey> {
  public List<AuditLog> getLogByItem(String tenant, String itemId);

  public AuditLog saveAuditLog(AuditLog auditLog);
}

