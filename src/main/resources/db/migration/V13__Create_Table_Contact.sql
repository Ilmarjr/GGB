
CREATE TABLE IF NOT EXISTS `contact` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `valid` bit DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
