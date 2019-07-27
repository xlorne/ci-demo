CREATE DATABASE IF NOT EXISTS test;

CREATE USER 'prod'@'localhost' IDENTIFIED BY 'prod';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'prod'@'localhost';


USE `test` ;


DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



