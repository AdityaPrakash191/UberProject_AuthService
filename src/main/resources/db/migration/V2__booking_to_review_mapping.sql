-- If there's a foreign key constraint, drop it first (replace `fk_review_id` with your actual foreign key name)
ALTER TABLE booking
DROP FOREIGN KEY FK_BOOKING_ON_REVIEW;

-- Step 3: Add booking column in booking_review table
ALTER TABLE booking_review
ADD COLUMN booking_id BIGINT NOT NULL;

-- Step 4: Add foreign key and unique constraint to establish one-to-one mapping
ALTER TABLE booking_review
ADD CONSTRAINT fk_booking_review_on_booking FOREIGN KEY (booking_id) REFERENCES booking (id);

ALTER TABLE booking_review
ADD CONSTRAINT uc_booking_review UNIQUE (booking_id);
-- Then drop the 'review_id' column ALTER TABLE booking DROP COLUMN review_id;

ALTER TABLE booking
DROP COLUMN review_id;