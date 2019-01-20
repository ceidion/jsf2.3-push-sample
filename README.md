# Publishing notification to all current logged in users or to a specific user using jsf 2.3

First create a docker network : docker network create mynet

Start keycloak container:

docker run --name authserver -d --net mynet -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=secret jboss/keycloak:3.4.1.Final

Login to

http://192.168.99.100:8080/auth/admin

and create portal realm, add a client with ID web and * to valid redirect uris. Create also the role auth_user and assign two users with that role.

Afterwards build the wildfly docker image

docker build --rm --tag=jboss/wildfly-admin .

and start the wildfly container: 

docker run --name wildfly -d --net mynet -p 8090:8080 -p 9990:9990 jboss/wildfly-admin

To see logs:

docker logs -f wildfly

To run the web application:

mvn clean package wildfly:deploy

Browse to : http://192.168.99.100:8090/jsf2.3-push/index.xhtml

Open two browsers and log in with different users. Application events are pushed to all clients, but user events target only the logged in user.

Keycloak uses as user id the ID auto-generated when creating a user in keycloak admin interface. So you need to insert the id of the user to publish a targeted notification.
