CREATE TABLE IF NOT EXISTS `tb_02_address` (
  `id` binary(16) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `customer_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_id` (`customer_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `tb_01_customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;