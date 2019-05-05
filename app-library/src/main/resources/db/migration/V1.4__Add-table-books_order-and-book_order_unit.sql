CREATE TABLE IF NOT EXISTS `books_order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `ready_to_release` BIT(1) NOT NULL DEFAULT b'0',
  `reader_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_books_order_1_idx` (`reader_id` ASC),
  CONSTRAINT `fk_books_order_1`
    FOREIGN KEY (`reader_id`)
    REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS `book_order_unit` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ready_to_rent` BIT(1) NOT NULL DEFAULT 0,
  `book_signature` BINARY(16) NOT NULL,
  `books_order_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_book_order_unit_1_idx` (`book_signature` ASC),
  INDEX `fk_book_order_unit_2_idx` (`books_order_id` ASC),
  CONSTRAINT `fk_book_order_unit_1`
    FOREIGN KEY (`book_signature`)
    REFERENCES `book_unit` (`signature`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_book_order_unit_2`
    FOREIGN KEY (`books_order_id`)
    REFERENCES `books_order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);