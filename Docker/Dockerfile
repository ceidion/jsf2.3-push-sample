FROM jboss/wildfly:13.0.0.Final

ENV KEYCLOAK_VERSION 3.4.1.Final

RUN /opt/jboss/wildfly/bin/add-user.sh admin secret --silent

WORKDIR /opt/jboss/wildfly

RUN curl -L https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-$KEYCLOAK_VERSION.tar.gz | tar zx
RUN curl -L https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/adapters/saml/keycloak-saml-wildfly-adapter-dist-$KEYCLOAK_VERSION.tar.gz | tar zx

COPY env-config/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone-full.xml

CMD ["/opt/jboss/wildfly/bin/standalone.sh","-Dee8.preview.mode=true", "-c", "standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]