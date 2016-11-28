package com.autodesk.commonlog.domain;

import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Table(value = "audit_log")
@JsonInclude(Include.NON_NULL)
public class AuditLog implements Serializable {

  // auto generated version id
  private static final long serialVersionUID = -7733224185463962252L;

  @PrimaryKey
  private AuditLogKey auditLogKey;

  @Column(value = "userid")
  private String userid;
  @Column(value = "session")
  private String session;
  @Column(value = "log_time")
  private Date logTime = Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant());
  @Column(value = "action_invoker")
  private String actionInvoker;
  @Column(value = "performedas_userid")
  private String performedasUserid;
  @Column(value = "update_details")
  private String updateDetails;

  public AuditLog() {
    this.auditLogKey = new AuditLogKey();
  }


  public AuditLogKey getAuditLogKey() {
    return auditLogKey;
  }

  public void setAuditLogKey(AuditLogKey auditLogKey) {
    this.auditLogKey = auditLogKey;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getSession() {
    return session;
  }

  public void setSession(String session) {
    this.session = session;
  }

  public Date getLogTime() {
    return logTime;
  }

  public void setLogTime(Date logTime) {
    this.logTime = logTime;
  }

  public String getActionInvoker() {
    return actionInvoker;
  }

  public void setActionInvoker(String actionInvoker) {
    this.actionInvoker = actionInvoker;
  }

  public String getPerformedasUserid() {
    return performedasUserid;
  }

  public void setPerformedasUserid(String performedasUserid) {
    this.performedasUserid = performedasUserid;
  }

  public String getUpdateDetails() {
    return updateDetails;
  }

  public void setUpdateDetails(String updateDetails) {
    this.updateDetails = updateDetails;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((auditLogKey == null) ? 0 : auditLogKey.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuditLog other = (AuditLog) obj;
    if (auditLogKey == null) {
      if (other.auditLogKey != null)
        return false;
    } else if (!auditLogKey.equals(other.auditLogKey))
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "AuditLog [auditLogKey=" + auditLogKey + "]";
  }



  }
