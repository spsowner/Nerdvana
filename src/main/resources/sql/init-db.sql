CREATE USER 'nerdvana'@'localhost' IDENTIFIED BY 'clear-text-pwd';
GRANT ALL PRIVILEGES ON *.* TO 'nerdvana'@'localhost' IDENTIFIED BY 'clear-text-pwd' WITH GRANT OPTION;
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('clear-text-pwd');
