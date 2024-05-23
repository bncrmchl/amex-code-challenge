#!/bin/bash
set -e

# Perform all actions as the 'postgres' user
psql -v ON_ERROR_STOP=1 --username "${POSTGRES_USER}" --dbname "${POSTGRES_DB}" <<-EOSQL

    -- Create the API user
    CREATE USER ${POSTGRES_API_USER} WITH LOGIN PASSWORD '${POSTGRES_API_PASSWORD}';

    -- Grant privileges
    GRANT CONNECT ON DATABASE ${POSTGRES_DB} TO ${POSTGRES_API_USER};
    GRANT USAGE ON SCHEMA public TO ${POSTGRES_API_USER};
    GRANT CREATE ON SCHEMA public TO ${POSTGRES_API_USER};


EOSQL
