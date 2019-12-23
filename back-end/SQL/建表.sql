DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    user_id      INT         NOT NULL AUTO_INCREMENT,
    username     VARCHAR(30) NOT NULL,
    password     VARCHAR(30) NOT NULL,
    avatar_url   TINYTEXT,
    email        VARCHAR(30) NOT NULL,
    introduction VARCHAR(50),
    gender       TINYINT     NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post`
(
    post_id   INT         NOT NULL AUTO_INCREMENT,
    poster_id INT         NOT NULL,
    title     VARCHAR(30) NOT NULL,
    star_num  INT         NOT NULL DEFAULT 0,
    views     INT         NOT NULL DEFAULT 0,
    PRIMARY KEY (`post_id`),
    FOREIGN KEY (`poster_id`) REFERENCES user (`user_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `post_content`;

CREATE TABLE `post_content`
(
    post_id INT      NOT NULL,
    floor   INT      NOT NULL,
    content LONGTEXT NOT NULL,
    time    DATETIME,
    PRIMARY KEY (`post_id`, `floor`),
    FOREIGN KEY (`post_id`) REFERENCES post (`post_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag`
(
    tag_name VARCHAR(30) NOT NULL,
    post_id  INT         NOT NULL,
    PRIMARY KEY (`tag_name`, `post_id`)
);

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question`
(
    question_id   INT      NOT NULL AUTO_INCREMENT,
    post_id       INT      NOT NULL,
    content       TEXT     NOT NULL,
    questioner_id INT      NOT NULL,
    time          DATETIME NOT NULL,
    PRIMARY KEY (`question_id`),
    FOREIGN KEY (`post_id`) REFERENCES post (`post_id`) ON DELETE CASCADE,
    FOREIGN KEY (`questioner_id`) REFERENCES user (`user_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer`
(
    answer_id   INT      NOT NULL AUTO_INCREMENT,
    question_id INT      NOT NULL,
    content     TEXT     NOT NULL,
    answerer_id INT      NOT NULL,
    time        DATETIME NOT NULL,
    PRIMARY KEY (`answer_id`),
    FOREIGN KEY (`question_id`) REFERENCES question (`question_id`) ON DELETE CASCADE,
    FOREIGN KEY (`answerer_id`) REFERENCES user (`user_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `favorite`;

CREATE TABLE `favorite`
(
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY (`user_id`, `post_id`),
    FOREIGN KEY (`user_id`) REFERENCES user (`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`post_id`) REFERENCES post (`post_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow`
(
    follower_id      INT NOT NULL,
    followed_user_id INT NOT NULL,
    PRIMARY KEY (`follower_id`, `followed_user_id`),
    FOREIGN KEY (`follower_id`) REFERENCES user (`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`followed_user_id`) REFERENCES user (`user_id`) ON DELETE CASCADE
);
