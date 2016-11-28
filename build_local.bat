set CASSANDRA_CONTACTPOINTS=127.0.0.1
set CASSANDRA_PORT=9042
set CASSANDRA_KEYSPACE=audit_log
set CASSANDRA_STRATEGY=SimpleStrategy
set CASSANDRA_REPLICATION_OPTIONS='replication_factor':1
set SERVER_PORT=8082
set LOG_DIR=./logs
set LOG_LEVEL=DEBUG
set CASSANDRA_CHANGE_LOG_FILE=/DatabaseChangeLog.xml
set WIP_DM_SERVICE_URL=https://developer-stg.api.autodesk.com/wipdata-serv-qa/storage/v3
set CLIENT_ID=AssmLUNjaCL73508gUGDzMIAPw5Adv71
set CLIENT_SECRET=epyiWLcQh6XAFWCC
set AUTH_URL=https://developer-stg.api.autodesk.com
set HYSTRIX_TIMEOUT=30000
set HYSTRIX_TIMEOUT_ENABLED=True
set SERVER_SERVLETPATH=/backOffice

gradle clean build