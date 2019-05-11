    ALTER TABLE `book_rental`
        ADD INDEX `fk_book_rental_book_order_unit_idx` (`book_order_unit_id` ASC) VISIBLE;
        ;
        ALTER TABLE `book_rental`
        ADD CONSTRAINT `fk_book_rental_book_order_unit`
          FOREIGN KEY (`book_order_unit_id`)
          REFERENCES `book_order_unit` (`id`)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION;