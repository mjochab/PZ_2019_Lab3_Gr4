ALTER DATABASE `urz_library_db`
CHARACTER SET utf8mb4
COLLATE utf8mb4_bin;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(64) NOT NULL,
  `street` VARCHAR(64) NOT NULL,
  `state` VARCHAR(64) NULL,
  `country` VARCHAR(64) NULL,
  `zip_code` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_id` INT NOT NULL,
  `first_name` VARCHAR(64) NOT NULL,
  `surname` VARCHAR(64) NOT NULL,
  `pesel` VARCHAR(11) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`, `role_id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `pesel_UNIQUE` (`pesel` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `library`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `director_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_library_user1_idx` (`director_id` ASC),
  INDEX `fk_library_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_library_user1`
    FOREIGN KEY (`director_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_library_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `publishing_company` VARCHAR(64) NOT NULL,
  `year_of_publication` SMALLINT NOT NULL,
  `library_id` INT NOT NULL,
  `author` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`, `library_id`),
  INDEX `fk_book_library1_idx` (`library_id` ASC),
  CONSTRAINT `fk_book_library1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `book_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `signature` VARCHAR(64) NOT NULL,
  `checked_out` BIT(1) NOT NULL DEFAULT 0,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`id`, `book_id`),
  UNIQUE INDEX `signature_UNIQUE` (`signature` ASC),
  INDEX `fk_book_unit_book1_idx` (`book_id` ASC),
  CONSTRAINT `fk_book_unit_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `book_rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_rental` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_of_rental` DATE NOT NULL,
  `date_of_return` DATE NOT NULL,
  `prolongation_number` TINYINT NOT NULL DEFAULT 0,
  `borrow_by_user` INT NOT NULL,
  `lend_by_user` INT NOT NULL,
  `book_unit_id` INT NOT NULL,
  PRIMARY KEY (`id`, `book_unit_id`),
  INDEX `fk_book_rental_user1_idx` (`borrow_by_user` ASC),
  INDEX `fk_book_rental_user2_idx` (`lend_by_user` ASC),
  INDEX `fk_book_rental_book_unit1_idx` (`book_unit_id` ASC),
  CONSTRAINT `fk_book_rental_user1`
    FOREIGN KEY (`borrow_by_user`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_rental_user2`
    FOREIGN KEY (`lend_by_user`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_rental_book_unit1`
    FOREIGN KEY (`book_unit_id`)
    REFERENCES `book_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `book_reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reserved_by_user` INT NOT NULL,
  `book_id` INT NOT NULL,
  `expires_at` DATE NOT NULL,
  PRIMARY KEY (`id`, `book_id`),
  INDEX `fk_book_reservation_user1_idx` (`reserved_by_user` ASC),
  INDEX `fk_book_reservation_book1_idx` (`book_id` ASC),
  CONSTRAINT `fk_book_reservation_user1`
    FOREIGN KEY (`reserved_by_user`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_reservation_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `employee_of_library`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee_of_library` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `library_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_employee_of_library_user1_idx` (`user_id` ASC),
  INDEX `fk_employee_of_library_library1_idx` (`library_id` ASC),
  CONSTRAINT `fk_employee_of_library_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_of_library_library1`
    FOREIGN KEY (`library_id`)
    REFERENCES `library` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);