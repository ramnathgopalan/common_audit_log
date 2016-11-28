package com.autodesk.commonlog.domain;


import java.io.Serializable;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

/**
 * Primary key class for the audit_log table
 * 
 *
 */
@PrimaryKeyClass
public class AuditLogKey implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -6131634815383568088L;
  @PrimaryKeyColumn(name = "tenant", ordinal = 0, ordering = Ordering.DESCENDING,
      type = PrimaryKeyType.PARTITIONED)
  private String tenant;

  @PrimaryKeyColumn(name = "item_id", ordinal = 1, ordering = Ordering.DESCENDING,
      type = PrimaryKeyType.PARTITIONED)
  private String itemId;

  @PrimaryKeyColumn(name = "resource_uri", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
  private String resourceUri;
  @PrimaryKeyColumn(name = "resource_method", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
  private String resourceMethod;
  @PrimaryKeyColumn(name = "log_id", ordinal = 4, type = PrimaryKeyType.CLUSTERED)
  private String logId;

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getResourceUri() {
    return resourceUri;
  }

  public void setResourceUri(String resourceUri) {
    this.resourceUri = resourceUri;
  }

  public String getResourceMethod() {
    return resourceMethod;
  }

  public void setResourceMethod(String resourceMethod) {
    this.resourceMethod = resourceMethod;
  }

  public String getLogId() {
    return logId;
  }

  public void setLogId(String logId) {
    this.logId = logId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
    result = prime * result + ((logId == null) ? 0 : logId.hashCode());
    result = prime * result + ((resourceMethod == null) ? 0 : resourceMethod.hashCode());
    result = prime * result + ((resourceUri == null) ? 0 : resourceUri.hashCode());
    result = prime * result + ((tenant == null) ? 0 : tenant.hashCode());
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
    AuditLogKey other = (AuditLogKey) obj;
    if (itemId == null) {
      if (other.itemId != null)
        return false;
    } else if (!itemId.equals(other.itemId))
      return false;
    if (logId == null) {
      if (other.logId != null)
        return false;
    } else if (!logId.equals(other.logId))
      return false;
    if (resourceMethod == null) {
      if (other.resourceMethod != null)
        return false;
    } else if (!resourceMethod.equals(other.resourceMethod))
      return false;
    if (resourceUri == null) {
      if (other.resourceUri != null)
        return false;
    } else if (!resourceUri.equals(other.resourceUri))
      return false;
    if (tenant == null) {
      if (other.tenant != null)
        return false;
    } else if (!tenant.equals(other.tenant))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AuditLogKey [tenant=" + tenant + ", itemId=" + itemId + ", resourceUri=" + resourceUri
        + ", resourceMethod=" + resourceMethod + ", logId=" + logId + "]";
  }


}
