CREATE TABLE IF NOT EXISTS booking
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    created_at           DATETIME NOT NULL,
    updated_at           DATETIME NOT NULL,
    review_id            BIGINT NULL,
    booking_status       ENUM('SCHEDULED','CANCELLED','CAB_ARRIVED','ASSIGNED_DRIVER','IN_RIDE','COMPLETED') NULL,
    start_date           DATE NULL,
    end_date             DATE NULL,
    total_distance_travelled       BIGINT NULL,
    driver_id            BIGINT NULL,
    passenger_id         BIGINT NULL,
    CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS booking_review
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    content    VARCHAR(255) NOT NULL,
    rating     DOUBLE NULL,
    CONSTRAINT pk_booking_review PRIMARY KEY (id)
);

CREATE TABLE driver
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     DATETIME NOT NULL,
    updated_at     DATETIME NOT NULL,
    name           VARCHAR(255) NULL,
    license_number VARCHAR(255) NOT NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

CREATE TABLE passenger_review
(
    passenger_review_id       BIGINT NOT NULL, -- Primary key, matches the @PrimaryKeyJoinColumn
    passenger_review_comment  VARCHAR(255) NOT NULL, -- Comment column
    passenger_review_rating   VARCHAR(255) NOT NULL, -- Rating column
    CONSTRAINT pk_passengerreview PRIMARY KEY (passenger_review_id) -- Primary key constraint
);
ALTER TABLE driver
    ADD CONSTRAINT uc_driver_license_number UNIQUE (license_number);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

ALTER TABLE booking
    ADD CONSTRAINT FK_BOOKING_ON_REVIEW FOREIGN KEY (review_id) REFERENCES booking_review (id);

ALTER TABLE passenger_review
    ADD CONSTRAINT fk_passengerreview_on_id FOREIGN KEY (passenger_review_id) REFERENCES booking_review (id);