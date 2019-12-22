INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (1, 'ywh', 1234, 'avatar/1.jpg', '1@a.com', 'I am ywh', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (2, 'lz', 1234, 'avatar/2.jpg', '2@a.com', 'I am lz', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (3, 'zdy', 1234, 'avatar/3.jpg', '3@a.com', 'I am zdy', 0);

INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (1, 1, 'Hello World', 3, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (2, 1, 'Another Hello World', 2, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (3, 2, 'Yet Another Hello World', 1, 0);

INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 1, 'post 1 floor 1', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 2, 'post 1 floor 2', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 3, 'post 1 floor 3', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 1, 'post 2 floor 1', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 2, 'post 2 floor 2', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (3, 1, 'post 3 floor 1', now());

INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag1', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag2', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag3', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag2', 2);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag3', 2);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag1', 3);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('tag2', 3);

INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'Why?', 1, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'What?', 2, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'How?', 3, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (2, 'Why??', 1, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (2, 'What??', 1, now());

INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (1, 1, '233', 1, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (2, 1, '2333', 2, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (3, 1, '23333', 3, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (4, 2, 'haha', 1, now());

INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (1, 2);
INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (1, 3);
INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (2, 1);
INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (2, 3);
INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (3, 1);
INSERT INTO `follow` (follower_id, followed_user_id)
VALUES (3, 2);

INSERT INTO `favorite` (user_id, post_id)
VALUES (1, 1);
INSERT INTO `favorite` (user_id, post_id)
VALUES (1, 2);
INSERT INTO `favorite` (user_id, post_id)
VALUES (1, 3);
INSERT INTO `favorite` (user_id, post_id)
VALUES (2, 1);
INSERT INTO `favorite` (user_id, post_id)
VALUES (2, 2);
INSERT INTO `favorite` (user_id, post_id)
VALUES (3, 1);


