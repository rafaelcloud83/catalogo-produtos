INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Rafael', 'Silva', 'rafael@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_category (name, created_At) VALUES ('Livros', NOW());
INSERT INTO tb_category (name, created_At) VALUES ('Eletrônicos', NOW());
INSERT INTO tb_category (name, created_At) VALUES ('Computadores', NOW());

INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('The Lord of the Rings', 90.5, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 'O Senhor dos Anéis é um livro de alta fantasia, escrito pelo escritor britânico J. R. R. Tolkien. Escrita entre 1937 e 1949, com muitas partes criadas durante a Segunda Guerra Mundial, a saga é uma continuação de O Hobbit.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/1.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Smart TV Philco 50', 2190.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Ouça, veja e sinta como nunca antes com a Fast Smart TV 50” Philco 4K Led PTV50G70SBLSG Dolby Audio! Ela possui processador Gráfico Triple Core com três núcleos que trabalham em conjunto com o processador do televisor, isso permite um melhor desempenho de imagens e respostas gráficas. Já o processador Quad core é um processador com quatro núcleos de processamento, permitindo um melhor desempenho da sua Televisão. Uma TV Fast, com resposta muito mais rápida aos comandos, seja para acessar a loja de Apps ou a internet. A Função Midiacast da Smart TV 50” Philco vai facilitar a interação, pois com ela você espelha seu dispositivo móvel na TV. Por falar em conexão, a Tv Philco tem conexão wireless e entradas USB e HDMI para projetar o que for preciso e apreciar com a qualidade da TV Smart Philco.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/2.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Macbook Pro', 20250.0, TIMESTAMP WITH TIME ZONE '2024-01-15T10:00:00Z', 'COM A POTÊNCIA DOS CHIPS M3 PRO OU M3 MAX O chip M3 Pro da Apple, com CPU de até 12 núcleos e GPU de até 18 núcleos com traçado de raios acelerado por hardware, oferece desempenho impressionante em tarefas pesadas, como manipular imagens panorâmicas enormes e compilar milhões de linhas de código. O chip M3 Max, com CPU de até 16 núcleos e GPU de até 40 núcleos, traz um rendimento excepcional para fluxos de trabalho extremos, como renderização 3D complexa ou desenvolvimento de modelos de transformação com bilhões de parâmetros.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/3.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer', 5200.0, TIMESTAMP WITH TIME ZONE '2023-06-14T10:00:00Z', 'Conheça o Pichau PC Gamer Poseidon, equipado com um processador AMD 5700X3D e 16GB de memória RAM. Este computador gamer entrega desempenho excepcional para jogos avançados e tarefas exigentes. Além disso, sua alta capacidade de memória RAM garante uma multitarefa suave e eficiente. Com SSD M.2 480GB, o Pichau Poseidon fornece tempos de inicialização extremamente rápidos, permitindo que você inicie suas partidas sem atrasos. Seu design robusto e contemporâneo adiciona um toque de elegância ao seu setup, enquanto o eficaz sistema de resfriamento mantém o computador em temperatura ideal durante sessões intensas de jogo.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/4.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('Rails for Dummies', 100.99, TIMESTAMP WITH TIME ZONE '2007-01-10T10:00:00Z', 'Se você precisa construir aplicativos Web e de banco de dados rapidamente, mas não sonha com código de computador, anime-se! Ruby on Rails foi criado para você, e este livro o colocará em funcionamento rapidamente. A linguagem de script Ruby e a estrutura Rails permitem criar rapidamente aplicativos Web completos. É até divertido!', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/5.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Ex', 1350.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/6.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer X', 1350.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/7.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Alfa', 1850.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/8.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Tera', 1950.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/9.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Y', 1700.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/10.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Nitro', 1450.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/11.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Card', 1850.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/12.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Plus', 1350.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/13.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Hera', 2250.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/14.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Weed', 2200.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/15.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Max', 2340.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/16.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Turbo', 1280.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/17.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Hot', 1450.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/18.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Ez', 1750.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/19.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Tr', 1650.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/20.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Tx', 1680.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/21.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Er', 1850.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/22.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Min', 2250.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/23.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Boo', 2350.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/24.jpg');
INSERT INTO tb_product (name, price, date, description, img_url) VALUES ('PC Gamer Foo', 4170.0, TIMESTAMP WITH TIME ZONE '2020-07-14T10:00:00Z', 'Um PC Gamer é um computador pessoal especialmente projetado para jogos de vídeo. Esses computadores são construídos com componentes de alta qualidade, como placas de vídeo poderosas, processadores rápidos, memória RAM suficiente e armazenamento rápido.', 'https://raw.githubusercontent.com/rafaelcloud83/catalogo-produtos/main/img/25.jpg');

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (7, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (8, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (9, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (10, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (11, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (12, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (13, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (14, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (15, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (16, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (17, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (18, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (19, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (20, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (21, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (22, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (23, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (24, 3);
INSERT INTO tb_product_category (product_id, category_id) VALUES (25, 3);
