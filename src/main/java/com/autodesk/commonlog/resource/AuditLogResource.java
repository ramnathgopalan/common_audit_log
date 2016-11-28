package com.autodesk.commonlog.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autodesk.commonlog.repo.AuditLogRepo;


@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auditlog/")
public class AuditLogResource {

  private static final Logger logger = LoggerFactory.getLogger(AuditLogResource.class);

  @Autowired
  private AuditLogRepo auditLogRepo;
  public static final String X_TENANT_HEADER = "x-tenant";
  public static final String X_USER_NAME = "x-user-name";
  public static final String AUTHORIZATION = "Authorization";


  @GET
  @Path("{itemId}")
  public Response getAJobStatus(@HeaderParam(AuditLogResource.X_TENANT_HEADER) String tenant,
      @PathParam("itemId") String itemId) {
    return Response.ok(auditLogRepo.getLogByItem(tenant, itemId)).build();
  }

}
