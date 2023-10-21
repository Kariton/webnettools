FROM ubuntu:latest as builder

ARG DEBIAN_FRONTEND=noninteractive

ARG SPEEDTEST_VERSION="1.2.0"

RUN apt-get update \
        && apt-get -yu dist-upgrade -y \
        && apt-get -yq install \
                maven \
                wget  \
                git

ADD . /root/webnettools/

WORKDIR /root/webnettools/

RUN mvn clean package


WORKDIR /root

RUN wget https://install.speedtest.net/app/cli/ookla-speedtest-${SPEEDTEST_VERSION}-linux-$(uname -m).tgz \
        && tar xvf ookla-speedtest-*.tgz

RUN git clone --depth 1 https://github.com/drwetter/testssl.sh.git

RUN git clone https://github.com/skavngr/rapidscan.git


FROM kalilinux/kali-last-release

RUN apt-get update \
        && apt-get -yu dist-upgrade -y \
        && apt-get -yq install \
		        dumb-init \
                traceroute \
                mtr \
                nmap

# for testssl.sh
RUN apt-get -yq install \
        openjdk-22-jre-headless \
		procps \
		bsdmainutils \
		iputils-ping \
        dnsutils

# for rapidscan
# 'golismero' install has been skipped. 
# See issue https://github.com/golismero/golismero/issues/59
RUN apt-get -yq install \
        python3 \
        host \
        whois \
        sslyze \
        wapiti \
        nmap \
        dmitry \
        dnsenum \
        dnsmap \
        dnsrecon \
        dnswalk \
        dirb \
        wafw00f \
        whatweb \
        nikto \
        lbd \
        xsser \
        fierce \
        theharvester \
        davtest \
        uniscan \
        amass \
        wget

RUN apt-get -yq autoremove \
        && apt-get clean \
		&& rm -rf /var/lib/{apt,dpkg,cache,log}

COPY --from=builder /root/webnettools/target/*-runner.jar /app/app.jar
COPY --from=builder /root/webnettools/target/lib/* /app/lib/

COPY --from=builder /root/testssl.sh /usr/local/bin/testssl.sh
RUN ln -s /usr/local/bin/testssl.sh/testssl.sh /usr/local/bin/testssl

COPY --from=builder /root/speedtest /usr/local/bin/

COPY --from=builder /root/rapidscan/rapidscan.py /usr/local/bin/rapidscan

WORKDIR /root

ENV AVAILABLE_TOOLS testssl,ping,traceroute,nmap,dig,mtr,speedtest,rapidscan
ENV CA_DIR /certs
ENV PORT 8080

EXPOSE 8080

HEALTHCHECK --start-period=60s CMD curl -f http://localhost:8080/q/health/live || exit 1

ENTRYPOINT ["dumb-init", "java"]
CMD ["-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}", "-jar", "/app/app.jar", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager"]
