INSERT INTO News (TITLE, DATE, DESCRIPTION) VALUES ('noticia 1', '2023-07-31', 'descrição da notícia 1');
INSERT INTO News (TITLE, DATE, DESCRIPTION) VALUES ('reportagem 1', '2023-07-28', 'descrição da notícia 2');
INSERT INTO News (TITLE, DATE, DESCRIPTION) VALUES ('noticia 2', '2023-07-31', 'descrição da notícia 3');
INSERT INTO tags (TAG, NEWS_ID) VALUES ('Tag1', 1);
INSERT INTO tags (TAG, NEWS_ID) VALUES ('Tag2', 1);
INSERT INTO tags (TAG, NEWS_ID) VALUES ('Tag3', 3);

INSERT INTO permission (DESCRIPTION) VALUES ('ADMIN');
INSERT INTO permission (DESCRIPTION) VALUES ('USER');
INSERT INTO users (`user_name`, `password`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`) VALUES ('ggbAdmin', '247ec302f9ea40e4922329cd86e6547de10be9411794448a528f4961c58f694a98a308a8a211b22d', true, true, true, true);
INSERT INTO user_permission (`id_user`,`id_permission`) VALUES (1,1);