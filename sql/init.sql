CREATE DATABASE IF NOT EXISTS test;


CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'root'@'localhost';

USE `t_test` ;


DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



