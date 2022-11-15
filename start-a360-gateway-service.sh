#!/bin/bash -e
#Gateway-service-start
java ${MAX_HEAP} -jar $APP_HOME/a360-gateway-service-0.0.1-SNAPSHOT.jar --a360.gateway.port=${A360_GATEWAY_PORT} --discovery.server.host=${DISCOVERY_SERVER_HOST} --discovery.server.port=${DISCOVERY_SERVER_PORT} --eureka.client.host=${EUREKA_CLIENT_HOST} --server.ssl.enabled=${SERVER_SSL_ENABLED} --eureka.non_secure_port=${EUREKA_NON_SECURE_PORT} --protocol.type=${PROTOCOL_TYPE}
