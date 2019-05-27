INSERT INTO `address` (`id`, `city`, `street`, `state`, `country`, `zip_code`) VALUES ('1', 'boss', 'boss', NULL, NULL, '12-345');
INSERT INTO `user` (`id`, `role_id`, `first_name`, `surname`, `pesel`, `email`, `password`, `address_id`)
                    VALUES ('1', '2', 'boss name', 'boss surname', '12345678910', 'boss@email.com', '$2a$12$yDZc1f.iL1lIBnobGc3buOFq0nVboev19GhrEwddGVW3iaVzP1GMS', '1');
