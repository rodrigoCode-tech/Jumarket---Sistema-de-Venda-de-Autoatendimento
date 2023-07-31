CREATE TABLE `venda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `forma_de_pagamento` varchar(30) NOT NULL,
  `valor_total` double NOT NULL,
  `cliente_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
);


CREATE TABLE `item_venda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `venda_id` bigint NOT NULL,
  `produto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_item_venda_venda` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`id`),
  CONSTRAINT `fk_item_venda_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
);