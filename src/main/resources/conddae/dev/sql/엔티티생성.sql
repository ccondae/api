use ccondae;

CREATE TABLE question (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title TEXT,
    purpose TEXT,
    code TEXT,
    github_url VARCHAR(255),
    content MEDIUMTEXT,
    like_count BIGINT DEFAULT 0,
    created_at TIMESTAMP,
    view_count BIGINT DEFAULT 0
);

CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content MEDIUMTEXT,
    like_count BIGINT DEFAULT 0,
    created_at TIMESTAMP,
    question_id BIGINT,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

create table question_category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    question_id BIGINT,
    category_id BIGINT,
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);