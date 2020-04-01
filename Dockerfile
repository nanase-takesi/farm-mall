FROM ubuntu:xenial
LABEL maintainer="takesi<nanasetakesi@yahoo.co.jp>"

RUN apt-get update -y

RUN apt-get install openjdk-8-jdk -y

RUN mkdir -p /opt/sym/jetty

ADD jetty/ /opt/sym/jetty/

ADD target/oldmantravel.war /opt/sym/jetty/webapps/

RUN chmod 755 /opt/sym/jetty/bin/jetty.sh

WORKDIR /opt/sym/
ENV TZ=Asia/Shanghai
EXPOSE 8080

ENTRYPOINT ["/bin/bash" ,"/opt/sym/jetty/bin/jetty.sh", "start"]
