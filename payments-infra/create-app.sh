mvn clean package -DskipTests -Dquarkus.container-image.build=true -Dquarkus.kubernetes-client.trust-certs=true

oc delete pod --field-selector=status.phase==Succeeded  

