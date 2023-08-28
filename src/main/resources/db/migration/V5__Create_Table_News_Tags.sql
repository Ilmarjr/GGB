
CREATE TABLE `news_tags` (
  `id_news` bigint NOT NULL,
  `id_tags` bigint NOT NULL,
  KEY `FK34vwf3rqd4ay74dgpnfrvbwst` (`id_tags`),
  KEY `FKj8ockevdgqygm5l3qesfvng26` (`id_news`),
  CONSTRAINT `FK34vwf3rqd4ay74dgpnfrvbwst` FOREIGN KEY (`id_tags`) REFERENCES `tags` (`id`),
  CONSTRAINT `FKj8ockevdgqygm5l3qesfvng26` FOREIGN KEY (`id_news`) REFERENCES `news` (`id`)
)
