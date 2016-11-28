CREATE KEYSPACE audit_log WITH replication = {'class': 'SimpleStrategy', 'replication_factor':1 };

  
CREATE TABLE if not exists audit_log (
   tenant text,
   item_id text,
-- eg /api/v3/workspace/8/items/555   
   resource_uri text,
-- 5 possible value GET,PUT,POST,DELETE,PATCH   
   resource_method text,
   log_id timeuuid,   
   userid text,
   session text,
   log_time timestamp,
   action_invoker text,
   performedas_userid text,
-- This is a in format fieldID~~oldValue~~newValue , Ony applicable for PUT   
   update_details list<text>,
   PRIMARY KEY ((tenant, item_id),resource_uri,resource_method, log_id)
);
  
  
insert into audit_log (tenant,item_id,resource_uri,resource_method,log_id,userid,session,log_time,action_invoker,performedas_userid,update_details)
 values (
'urn:adsk.plm:tenant:DEVINDMACHINE',
'urn:adsk.plm:tenant.workspace.item:DEVINDMACHINE.1.1000',
'/api/v3/workspaces/1/items/1000',
'POST',
014ba760-aafa-11e6-9de5-8bdc44562dfa,
'plmautotest',
'sessionid',
dateof(now()),
'plmautotest',
'plmautotest',
null);