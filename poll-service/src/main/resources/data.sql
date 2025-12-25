INSERT INTO poll (question_title, option_one, option_two, option_three, option_four)
VALUES ('Between the following, what do you most love to do?', '1. Watch TV', '2. Play the computer', '3. Hanging out with friends', '4. Travel the world'),
       ('Between the following, where would you like to travel the most?', '1. Japan', '2. USA', '3. France', '4. Korea');

INSERT INTO poll_answers (user_id, poll_id, selected_option)
VALUES (10, 1, 4),
       (11, 1, 2),
       (10, 2, 2);