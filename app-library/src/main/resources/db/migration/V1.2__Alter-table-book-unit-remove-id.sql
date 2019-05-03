ALTER TABLE `book_rental`
    DROP FOREIGN KEY `fk_book_rental_book_unit1`,
    DROP INDEX `fk_book_rental_book_unit1_idx`;

ALTER TABLE `book_unit`
    MODIFY id INT NOT NULL,
    MODIFY `signature` BINARY(16) NOT NULL,
    DROP INDEX `signature_UNIQUE`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`signature`);

ALTER TABLE `book_unit` DROP COLUMN `id`;

ALTER TABLE `book_rental`
    DROP COLUMN `book_unit_id`,
    ADD COLUMN `book_signature` BINARY(16) NOT NULL,
    ADD INDEX `fk_book_rental_book_signature_idx` (`book_signature` ASC),
    ADD CONSTRAINT `fk_book_rental_book_book_signature1`
        FOREIGN KEY (`book_signature`)
        REFERENCES `book_unit`(`signature`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;