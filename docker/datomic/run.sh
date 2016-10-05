#!/usr/bin/env bash

cat << EOF > /etc/datomic/transactor.properties
license-key=${DATOMIC_LICENSE_KEY}
protocol=free
host=0.0.0.0
port=4334
memory-index-threshold=32m
memory-index-max=256m
object-cache-max=128m
EOF

/etc/datomic/bin/transactor /etc/datomic/transactor.properties