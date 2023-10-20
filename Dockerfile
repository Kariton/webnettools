FROM ubuntu:latest as builder

ARG DEBIAN_FRONTEND=noninteractive

ARG SPEEDTEST_VERSION="1.2.0"

ADD . /root/webnet-tools/

RUN apt-get update \
        && apt-get -yq install \
                maven \
                wget  \
                git \
        && cd /root/webnet-tools \
        && mvn clean package

RUN cd /root \
        && wget https://install.speedtest.net/app/cli/ookla-speedtest-${SPEEDTEST_VERSION}-linux-$(uname -m).tgz \
        && tar xvf *.tgz

RUN cd /root \
        && git clone --depth 1 https://github.com/drwetter/testssl.sh


FROM openjdk:22-jdk-slim-bullseye

RUN apt-get update \
        && apt-get -yq install \
		        dumb-init \
				procps \
		        bsdmainutils \
				iputils-ping \
                traceroute \
                mtr \
                dnsutils \
                nmap \
        && apt-get clean

COPY --from=builder /root/webnet-tools/target/*-runner.jar /app/app.jar
COPY --from=builder /root/webnet-tools/target/lib/* /app/lib/
COPY --from=builder /root/testssl.sh /app/testssl.sh
COPY --from=builder /root/speedtest /usr/local/bin/

WORKDIR /root

ENV PATH /app/testssl.sh/:$PATH
ENV AVAILABLE_TOOLS testssl,ping,traceroute,nmap,dig,mtr,speedtest
ENV CA_DIR /certs
ENV PORT 8080

EXPOSE 8080

HEALTHCHECK --start-period=60s CMD curl -f http://localhost:8080/q/health/live || exit 1

ENTRYPOINT ["dumb-init", "java"]
CMD ["-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}", "-jar", "/app/app.jar", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager"]
