# Backup
docker exec parking_db_1 /usr/bin/mysqldump -u test_user --password=pass test_base > backup.sql

# Restore
cat backup.sql | docker exec -i parking_db_1 /usr/bin/mysql -u test_user --password=pass test_base