FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/artistas_e_albuns_api

COPY /target/artistas-e-albuns*.jar artistas_e_albuns_api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar artistas_e_albuns_api.jar --spring.profiles.active=${PROFILE}