CREATE TABLE `carrinho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_carrinho_cliente` (`cliente_id`),
  CONSTRAINT `fk_carrinho_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
);

CREATE TABLE `item_carrinho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `carrinho_id` bigint NOT NULL,
  `produto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_item_carrinho_carrinho` FOREIGN KEY (`carrinho_id`)
  REFERENCES `carrinho` (`id`),
  CONSTRAINT `fk_item_carrinho_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
);