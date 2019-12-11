INSERT INTO endereco (nome, estado) VALUES ('Pato Branco', 'PR');
INSERT INTO endereco (nome, estado) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO endereco (nome, estado) VALUES ('Belo Horizonte', 'MG');
INSERT INTO endereco (nome, estado) VALUES ('São Paulo', 'SP');
INSERT INTO endereco (nome, estado) VALUES ('Salvador', 'BA');
INSERT INTO endereco (nome, estado) VALUES ('Porto Alegre', 'RS');
INSERT INTO endereco (nome, estado) VALUES ('Goiânia', 'GO');
INSERT INTO endereco (nome, estado) VALUES ('Curitiba', 'PR');


INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('1A', 1, 1, 'ECONOMICO', 50.00);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('2A', 1, 2, 'ECONOMICO', 75.00);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('3A', 4, 8, 'ECONOMICO', 150.00);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('4A', 2, 4, 'ECONOMICO', 200.00);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('1B', 2, 2, 'SUPERIOR', 250.5);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('2B', 4, 4, 'SUPERIOR', 400.5);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('3A', 2, 2, 'LUXO', 999.99);
INSERT INTO quarto (numero, qtdeCamas, qtdePessoas, tipo, valorDiaria) VALUES ('3B', 4, 4, 'LUXO', 998.99);


INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Produtos', 'Água Mineral sem gás.', 'Agua Mineral', 4.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Servicos', 'Limpar o quarto em momentos não designados.', 'Limpeza de Quarto', 40.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Produtos', 'Refrigerante de qualquer sabor', 'Refrigerante', 8.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Produtos', 'Lanchinho da tarde fora de horário.', 'Lanche', 6.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Produtos', 'Sobremesa do dia fora de horário.', 'Sobremesas', 15.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Servicos', 'Massagem ao som de música relaxante.', 'Massagem', 60.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Servicos', 'Usar a sauna para tratamento da pele e bem-estar.', 'Sauna', 20.00);
INSERT INTO produtos (categoria, descricao, nome, valor) VALUES ('Servicos', 'Usar e abusar das Fontes Termais estilo Japonesas', 'Fontes Termais', 99.00);


--INSERT INTO cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) 
--VALUES ('128.616.656.02', 'xandim@gmail.com', 'Pato Branco - PR', 'Xandim', '13254678', '15612318', '46988088147', '46988012342');
--INSERT INTO cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) 
--VALUES ('123.345.656.23', 'lucrecil@gmail.com', 'Rio de Janeiro - RJ', 'Lucrecil', '13254678', '89424891', '46988088147', '46988012342');
--INSERT INTO cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) 
--VALUES ('543.867.213.06', 'dobby@gmail.com', 'Curitiba - PR', 'Dobby', '13254678', '82148491', '46988088147', '46988012342');

insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('201.283.965-28', 'fmaw0@com.com', 'Salvador - BA', 'Farly Maw', 'BR177830', '56.389.740-1', '(16) 90065-5267', '(37) 95370-5631');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('846.708.417-92', 'ltrusler1@moonfruit.com', 'Curitiba - PR', 'Lorrie Trusler', 'BR417590', '18.240.182-9', '(78) 99960-2281', '(90) 91482-6618');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('527.405.847-83', 'mfriel2@hp.com', 'Salvador - BA', 'Murdock Friel', 'BR697714', '32.203.523-3', '(35) 92622-0522', '(87) 93177-0250');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('775.618.423-90', 'jengley3@hexun.com', 'Belo Horizonte - MG', 'Jacklyn Engley', 'BR331633', '55.746.624-8', '(97) 93300-3906', '(10) 93490-0799');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('746.926.347-55', 'ycoard4@cargocollective.com', 'Belo Horizonte - MG', 'Yance Coard', 'BR855240', '19.323.474-6', '(79) 94649-2715', '(31) 92575-2368');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('217.347.997-82', 'tsedgemore5@home.pl', 'Rio de Janeiro - RJ', 'Terese Sedgemore', 'BR518965', '05.165.418-9', '(62) 98766-6325', '(49) 96662-3665');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('659.894.203-94', 'mmelmar6@rakuten.co.jp', 'Porto Alegre - RS', 'Melodee Melmar', 'BR106548', '91.962.115-2', '(21) 94827-3669', '(68) 97342-2185');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('280.669.165-45', 'mcosgriff7@ehow.com', 'Rio de Janeiro - RJ', 'Madalena Cosgriff', 'BR137218', '22.199.432-7', '(41) 94285-9166', '(64) 90646-6286');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('256.126.646-47', 'lmacwhirter8@godaddy.com', 'Belo Horizonte - MG', 'Ludvig MacWhirter', 'BR839713', '84.054.237-1', '(35) 92964-9119', '(52) 92013-1348');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('204.523.387-15', 'ftweedie9@sphinn.com', 'Curitiba - PR', 'Farley Tweedie', 'BR777353', '89.213.113-2', '(79) 98733-5386', '(91) 90281-3954');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('025.147.949-57', 'jselwooda@si.edu', 'Belo Horizonte - MG', 'Janos Selwood', 'BR900179', '74.180.272-9', '(80) 94714-8084', '(97) 93650-5852');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('096.482.530-05', 'rlampkeb@wunderground.com', 'Porto Alegre - RS', 'Rudolfo Lampke', 'BR735483', '38.186.103-1', '(39) 90104-8873', '(36) 90669-1275');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('908.202.339-09', 'vswinnardc@chronoengine.com', 'Rio de Janeiro - RJ', 'Verile Swinnard', 'BR482902', '13.061.650-2', '(29) 99131-1595', '(87) 97192-3114');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('021.500.024-35', 'vbylesd@surveymonkey.com', 'Goiânia - GO', 'Verina Byles', 'BR745257', '21.692.885-2', '(51) 90032-9060', '(04) 93553-0918');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('727.851.826-45', 'pwipere@dailymotion.com', 'Belo Horizonte - MG', 'Philis Wiper', 'BR283796', '42.984.199-5', '(09) 93920-9428', '(93) 97759-6208');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('967.090.944-68', 'fbrunsdenf@wunderground.com', 'Pato Branco - PR', 'Fidelio Brunsden', 'BR714587', '23.283.767-9', '(33) 94647-3747', '(39) 97061-8973');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('044.422.810-33', 'wsolomong@squarespace.com', 'Porto Alegre - RS', 'Willis Solomon', 'BR622748', '90.496.384-1', '(22) 91039-2487', '(39) 97466-4261');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('726.882.582-79', 'jcottageh@usnews.com', 'Curitiba - PR', 'Jody Cottage', 'BR456286', '98.775.464-4', '(79) 98486-4478', '(17) 96235-4036');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('200.088.251-50', 'ckeirlei@tripadvisor.com', 'Pato Branco - PR', 'Celle Keirle', 'BR287893', '25.547.072-4', '(55) 91619-3624', '(69) 93531-5890');
insert into cliente (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('572.952.591-99', 'kvossej@biglobe.ne.jp', 'São Paulo - SP', 'Kalina Vosse', 'BR301491', '90.353.948-2', '(17) 92093-5915', '(54) 94773-4924');


--INSERT INTO hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) 
--VALUES ('797.780.484-01', 'eduardoenricodiegolima@tahoo.com', 'São Paulo - SP', 'Eduardo Enrico Diego Lima', '40.585.324-5', '15612318', '(11) 3777-0431', '(11) 99267-0249');
--INSERT INTO hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) 
--VALUES ('252.397.463-03', 'antoniodavimartins@uniara.com', 'Rio de Janeiro - RJ', 'Antonio Davi Martins', '324524', '29.212.839-3', '(69) 3729-5783', '(69) 98374-8229');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('565.022.332-43', 'adalzell0@cnet.com', 'Porto Alegre - RS', 'Audrie Dalzell', 'BR963901', '38.166.284-3', '(45) 96563-6949', '(93) 98031-3102');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('912.504.620-07', 'ehounsom1@chron.com', 'Porto Alegre - RS', 'Elyse Hounsom', 'BR282870', '82.409.823-0', '(48) 91082-2605', '(03) 91582-8533');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('720.184.037-32', 'kmealand2@gmpg.org', 'Pato Branco - PR', 'Karney Mealand', 'BR009853', '64.444.817-7', '(89) 95430-9555', '(03) 98089-1619');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('971.872.094-40', 'wwurz3@aboutads.info', 'Porto Alegre - RS', 'Winfield Wurz', 'BR909678', '47.533.113-5', '(33) 93968-0664', '(88) 96787-5262');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('259.518.457-12', 'bciardo4@google.it', 'São Paulo - SP', 'Batholomew Ciardo', 'BR949531', '26.137.979-9', '(26) 93302-0036', '(88) 97825-9620');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('652.914.454-92', 'chebborn5@flickr.com', 'Curitiba - PR', 'Collie Hebborn', 'BR992060', '88.696.485-9', '(44) 95971-3023', '(70) 97277-3545');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('537.023.898-23', 'asawtell6@prlog.org', 'Rio de Janeiro - RJ', 'Arv Sawtell', 'BR258141', '92.998.131-7', '(61) 91464-6559', '(12) 92413-3625');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('032.349.078-31', 'kphilippart7@deliciousdays.com', 'Salvador - BA', 'Kimberlee Philippart', 'BR520685', '51.827.529-5', '(37) 94715-1851', '(65) 92982-8812');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('220.899.188-30', 'atax8@nifty.com', 'Salvador - BA', 'Arman Tax', 'BR127406', '52.757.458-3', '(68) 95572-8350', '(35) 99608-0331');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('386.523.333-78', 'eadamovitch9@army.mil', 'Goiânia - GO', 'Ethyl Adamovitch', 'BR822620', '76.854.143-7', '(69) 90989-0609', '(75) 96954-2868');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('664.310.610-71', 'awicklinga@com.com', 'Curitiba - PR', 'Arleyne Wickling', 'BR074969', '59.680.310-3', '(93) 94061-9098', '(57) 98063-3873');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('561.427.277-45', 'nburrowb@list-manage.com', 'Porto Alegre - RS', 'Noby Burrow', 'BR861916', '26.954.063-9', '(42) 94057-4016', '(70) 98275-3660');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('897.039.786-74', 'sswindinc@joomla.org', 'Salvador - BA', 'Siouxie Swindin', 'BR199789', '78.891.346-6', '(65) 90679-3382', '(60) 95965-7514');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('177.899.458-10', 'jbaskervilled@facebook.com', 'Porto Alegre - RS', 'Jocelyn Baskerville', 'BR348596', '31.849.421-9', '(80) 92588-0252', '(74) 99415-8545');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('425.022.358-22', 'rfurnisse@uiuc.edu', 'São Paulo - SP', 'Randee Furniss', 'BR796653', '09.644.905-1', '(42) 94755-9710', '(73) 99064-4848');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('723.632.585-24', 'elorenzinf@jigsy.com', 'Curitiba - PR', 'Emanuele Lorenzin', 'BR593953', '06.999.590-5', '(66) 93776-1712', '(70) 97264-7543');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('097.921.986-52', 'cmarquandg@t-online.de', 'Salvador - BA', 'Cobbie Marquand', 'BR276962', '62.313.722-6', '(41) 90757-4628', '(93) 94608-2634');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('014.678.608-45', 'sprierh@msn.com', 'Salvador - BA', 'Stacee Prier', 'BR527638', '02.806.976-3', '(34) 95866-5320', '(41) 95297-7773');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('186.273.863-58', 'cbuckleyi@ihg.com', 'São Paulo - SP', 'Catrina Buckley', 'BR141317', '40.665.303-7', '(91) 94540-7224', '(52) 95495-3247');
insert into hospede (cpf, email, endereco, nome, numeropassaporte, rg, telefonecomercial, telefoneresidencial) values ('637.153.121-16', 'emendenhallj@tinyurl.com', 'Belo Horizonte - MG', 'Ernesta Mendenhall', 'BR988915', '17.742.959-2', '(02) 95752-7932', '(07) 92642-0735');

insert into usuario (ativo, cpf, datanascimento, email, nome, senha) values ('T', '201.283.965-28', '2002-12-03', 'nn@a.com', 'Xandim', '1234');
