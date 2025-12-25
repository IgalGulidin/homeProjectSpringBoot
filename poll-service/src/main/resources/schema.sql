DROP TABLE IF EXISTS poll;
DROP TABLE IF EXISTS poll_answers;

CREATE TABLE poll (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_title VARCHAR(255) NOT NULL DEFAULT '',
    option_one VARCHAR(255) NOT NULL DEFAULT '',
    option_two VARCHAR(255) NOT NULL DEFAULT '',
    option_three VARCHAR(255) NOT NULL DEFAULT '',
    option_four VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE poll_answers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    poll_id INT NOT NULL,
    selected_option INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT foreign_key_poll_answers_poll
        FOREIGN KEY (poll_id) REFERENCES poll(id)
        ON DELETE CASCADE,

    CONSTRAINT check_selected_option
        CHECK (selected_option BETWEEN 1 AND 4),

    CONSTRAINT unique_user_poll
        UNIQUE (user_id, poll_id)
);