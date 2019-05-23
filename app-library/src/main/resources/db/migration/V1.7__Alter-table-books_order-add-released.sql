ALTER TABLE `urz_library_db`.`books_order`
ADD COLUMN `released` BIT(1) NULL DEFAULT 0 AFTER `reader_id`;
