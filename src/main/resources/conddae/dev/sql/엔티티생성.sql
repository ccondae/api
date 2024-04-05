use ccondae;

CREATE TABLE question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title TEXT,
    text MEDIUMTEXT,
    created_at TIMESTAMP,
    view_count BIGINT DEFAULT 0
);

CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text MEDIUMTEXT,
    question_id BIGINT,
    created_at TIMESTAMP,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

create table question_category (
    question_id BIGINT,
    category_id INT,
    PRIMARY KEY (question_id, category_id),
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);