oc new-app mysql:8.0~https://github.com/lematthe/payment-hub\
    --name my-mysql-rhel8 \
    --context-dir=payment-db  \
    --env MYSQL_OPERATIONS_USER=opuser \
    --env MYSQL_OPERATIONS_PASSWORD=oppass \
    --env MYSQL_DATABASE=payments \
    --env MYSQL_USER=payuser \
    --env MYSQL_PASSWORD=payp@33