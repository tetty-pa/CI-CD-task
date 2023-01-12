drop table gift_certificate_has_tag;
drop table `gift_certificate`;
drop table `tag`;

-- -----------------------------------------------------
-- Table `rest-api`.`gift_certificate`
-- -----------------------------------------------------
CREATE TABLE `gift_certificate`
(
    `id`                INT          NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(45)  NULL DEFAULT NULL,
    `description`       VARCHAR(255) NULL DEFAULT NULL,
    `price`             INT          NULL DEFAULT NULL,
    `duration`          INT          NULL DEFAULT NULL,
    `create_date`       DATETIME     NULL DEFAULT NULL,
    `last_updated_date` DATETIME     NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `rest-api`.`tag`
-- -----------------------------------------------------
CREATE TABLE `tag`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `rest-api`.`gift_certificate_has_tag`
-- -----------------------------------------------------
CREATE TABLE `gift_certificate_has_tag`
(
    `gift_certificate_id` INT NOT NULL,
    `tag_id`              INT NOT NULL,
    PRIMARY KEY (`gift_certificate_id`, `tag_id`),
    CONSTRAINT `fk_gift_certificate_has_tag_gift_certificate`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES `gift_certificate` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_gift_certificate_has_tag_tag1`
        FOREIGN KEY (`tag_id`)
            REFERENCES `tag` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);
