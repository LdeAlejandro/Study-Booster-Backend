ALTER TABLE preferences DROP COLUMN label;
ALTER TABLE preferences ADD COLUMN module_name VARCHAR(255);