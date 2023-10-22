### ### ### ### ### ### ### ### ###
# BUILD                           #
### ### ### ### ### ### ### ### ###
FROM debian:bookworm as builder
ARG DEBIAN_FRONTEND=noninteractive
ARG SPEEDTEST_VERSION="1.2.0"

RUN apt-get update \
        && apt-get -yu dist-upgrade -y \
        && apt-get -yq install \
                wget  \
                git

WORKDIR /root
RUN wget https://install.speedtest.net/app/cli/ookla-speedtest-${SPEEDTEST_VERSION}-linux-$(uname -m).tgz \
        && tar xvf ookla-speedtest-${SPEEDTEST_VERSION}-linux-$(uname -m).tgz
RUN git clone --depth 1 https://github.com/drwetter/testssl.sh.git
RUN git clone https://github.com/skavngr/rapidscan.git

### ### ###

# don't build on debian...
# 'Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project webnettools: Fatal error compiling: java.lang.IllegalAccessError: class lombok.javac.apt.LombokProcessor (in unnamed module @0x18bb9243) cannot access class com.sun.tools.javac.processing.JavacProcessingEnvironment (in module jdk.compiler) because module jdk.compiler does not export com.sun.tools.javac.processing to unnamed module @0x18bb9243'
#FROM debian:bookworm as maven-builder
FROM ubuntu:latest as maven-builder
ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update \
        && apt-get -yu dist-upgrade -y \
        && apt-get -yq install \
                maven

ADD . /root/webnettools/
WORKDIR /root/webnettools/
RUN mvn clean package

### ### ###

FROM golang:bookworm AS go-builder
ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update \
        && apt-get -yu dist-upgrade -y \
        && apt-get -yq install \
            git

# nuclei
WORKDIR /root
RUN git clone https://github.com/projectdiscovery/nuclei.git
WORKDIR /root/nuclei
RUN go mod download
RUN go build ./cmd/nuclei

# wcvs
WORKDIR /root
RUN git clone https://github.com/Hackmanit/Web-Cache-Vulnerability-Scanner.git
WORKDIR /root/Web-Cache-Vulnerability-Scanner/
RUN go get -d -v ./...
RUN go build -o wcvs


### ### ### ### ### ### ### ### ###
# CONTAINER                       #
### ### ### ### ### ### ### ### ###
FROM kalilinux/kali-last-release
ARG DEBIAN_FRONTEND=noninteractive

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

# for nuclei
RUN apt-get -yq install \
        chromium \
        dnsutils

RUN apt-get -yq autoremove \
        && apt-get clean \
	&& rm -rf /var/lib/{apt,dpkg,cache,log}

# testssl
COPY --from=builder /root/testssl.sh /usr/local/bin/testssl.sh
RUN ln -s /usr/local/bin/testssl.sh/testssl.sh /usr/local/bin/testssl

# speedtest
COPY --from=builder /root/speedtest /usr/local/bin/

# rapidscan
COPY --from=builder /root/rapidscan/rapidscan.py /usr/local/bin/rapidscan

# nuclei
COPY --from=go-builder /root/nuclei/nuclei /usr/local/bin/

# wcvs
COPY --from=go-builder /root/Web-Cache-Vulnerability-Scanner/wcvs /usr/local/bin/
COPY --from=go-builder /root/Web-Cache-Vulnerability-Scanner/wordlists/ /usr/local/bin/wordlists/

# webnettools
COPY --from=maven-builder /root/webnettools/target/*-runner.jar /app/app.jar
COPY --from=maven-builder /root/webnettools/target/lib/* /app/lib/

WORKDIR /root

ENV AVAILABLE_TOOLS testssl,ping,traceroute,nmap,dig,mtr,speedtest,rapidscan,nuclei,wcvs
ENV CA_DIR /certs
ENV PORT 8080

EXPOSE 8080

HEALTHCHECK --start-period=60s CMD curl -f http://localhost:8080/q/health/live || exit 1

ENTRYPOINT ["dumb-init", "java"]
CMD ["-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=${PORT}", "-jar", "/app/app.jar", "-Djava.util.logging.manager=org.jboss.logmanager.LogManager"]
