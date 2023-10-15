CREATE TABLE IF NOT EXISTS `tb_01_customer` (
  `id` binary(16) NOT NULL,
  `birthdate` datetime(6) DEFAULT NULL,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `rg` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK-cpf` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;