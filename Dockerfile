FROM sathish10218/alpine-java:latest

VOLUME /tmp

ARG PORT=7000

ARG GATEWAY_SERVICE_JAR=./target/a360-gateway-service-0.0.1-SNAPSHOT.jar

COPY ${GATEWAY_SERVICE_JAR} start-a360-gateway-service.sh /tmp/

ENV APP_HOME /tmp/
ENV PORT ${PORT}

EXPOSE ${PORT}

RUN apk update \
    && apk add --update graphviz ttf-ubuntu-font-family --no-cache bash  \
    && mkdir /dot \
    && chmod a+x /tmp/*.sh \
    && mv /tmp/start-a360-gateway-service.sh /usr/bin \
    && rm /tmp/*

CMD dot -Tpng

ENTRYPOINT [ "start-a360-gateway-service.sh" ]
