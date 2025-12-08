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
    FOREIGN KEY (poll_id) REFERENCES poll(id)
);