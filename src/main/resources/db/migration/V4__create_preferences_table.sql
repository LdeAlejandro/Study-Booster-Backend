CREATE TABLE preferences (
    id SERIAL PRIMARY KEY,
    label VARCHAR(255),
    interval VARCHAR(20) NOT NULL,
    subject_id BIGINT,
    module_id BIGINT,
    CONSTRAINT fk_subject FOREIGN KEY (subject_id) REFERENCES subjects(id),
    CONSTRAINT fk_module FOREIGN KEY (module_id) REFERENCES content_modules(id)
);
