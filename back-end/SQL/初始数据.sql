# User
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (1, 'ywh', 123456, 'avatar/1.jpg', '1@a.com', 'I am ywh', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (2, 'lz', 123456, 'avatar/2.jpg', '2@a.com', 'I am lz', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (3, 'zdy', 123456, 'avatar/3.jpg', '3@a.com', 'I am zdy', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (4, 'sgq', 123456, 'avatar/4.jpg', '4@a.com', 'I am sgq', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (5, 'ssz', 123456, 'avatar/5.jpg', '5@a.com', 'I am ssz', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (6, 'lx', 123456, 'avatar/6.jpg', '6@a.com', 'I am lx', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (7, 'cxk', 123456, 'avatar/7.jpg', '7@a.com', 'I am cxk', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (8, 'wfy', 123456, 'avatar/8.jpg', '8@a.com', 'I am wyf', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (9, 'wlh', 123456, 'avatar/9.jpg', '9@a.com', 'I am wlh', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (10, 'lh', 123456, 'avatar/10.jpg', '10@a.com', 'I am lh', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (11, 'xm', 123456, 'avatar/11.jpg', '11@a.com', 'I am zhm', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (12, 'lyr', 123456, 'avatar/12.jpg', '12@a.com', 'I am fhf', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (13, 'ssr', 123456, 'avatar/13.jpg', '13@a.com', 'I am ssr', 1);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (14, 'godlike', 123456, 'avatar/14.jpg', '14@a.com', 'I am godlike', 0);
INSERT INTO `user` (user_id, username, password, avatar_url, email, introduction, gender)
VALUES (15, 'blsd', 123456, 'avatar/15.jpg', '15@a.com', 'I am blsd', 1);

# Post
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (1, 1, 'Hello World', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (2, 2, 'Another Hello World', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (3, 3, 'Yet Another Hello World', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (4, 4, 'Today do Data Warehouse', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (5, 5, 'Today do Java EE', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (6, 1, 'Tomorrow do cloud computing', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (7, 2, 'Software Engineering', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (8, 1, 'Today do swimming', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (9, 2, 'Tomorrow do running', 0, 0);
INSERT INTO `post` (post_id, poster_id, title, star_num, views)
VALUES (10, 1, 'Sony No.1', 0, 0);

INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 1, '25岁时，没人跟我说，知识或者技能这种东西，学到了就跟你一辈子，不管工作或时空跨度多大，新知和技能都能排上用场', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 2, '25岁时，有人跟我说，习惯很重要，但没人跟我说重要到深度影响自己生活、人生选择和生命质量的程度。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 3, '因为几乎大多数人，每时每刻都生活在形形色色的思维习惯、生活习惯中', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 4, '比如健身、跑步、阅读、表达、写作、沟通、学习等等', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 5, '所有影响我们生活工作结果的全是这些习惯', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 6, '我们养成习惯，然后习惯养成我们。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 7, '25岁时，没人跟我说，围剿式学习，就是吸星大法，', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 8, '通过观察对方的沟通、演讲、写作方式，从而学习他们的思维方式、学习他们身上最优秀的思维习惯。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 9, '25岁时，没人跟我说，真诚和诚信有多么重要，真诚是领导力的真谛，诚信是商业的真谛', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (1, 10, '每个人的年薪、领导能力、商业成就全部都可以从这两个词汇里诠释。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 1, '25岁时，没人跟我说，父母正在逼近死亡。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 2, '年轻人可能心里想得更多的是幸福生活，但父母的年纪，他们年龄越大，越要去面临离开这一主题，他们担心被世界抛弃', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 3, '担心自己在儿女生活中扮演不了什么重要价值，特别渴望回馈、关注和尊重', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 4, '不管多忙，能多打一个电话就多打一个电话', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (2, 5, '能多在一起吃顿饭，就多在一起吃顿饭，', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (3, 1, '能满足他们想要的，不管大小，尽量去满足。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (4, 1, '25岁时，没人跟我说，睡眠其实真正决定了生命的效率。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (5, 1, '因为睡眠决定着第二天的心情、状态、专注度等', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (6, 1, '而心情、状态、专注度直接影响结果', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (7, 1, '我们的现在就是由大大小小的这些结果构成的。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (8, 1, '所以睡个好觉可能是让生活变得更好的最大的捷径。', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (9, 1, '25岁时，没人跟我说，保持自我，并不断变得更好，', now());
INSERT INTO `post_content` (post_id, floor, content, time)
VALUES (10, 1, '其实在一段感情里，远比委曲求全，一味地宠溺对方，更重要。', now());


INSERT INTO `tag` (tag_name, post_id)
VALUES ('Chinese', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Math', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('English', 1);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Huaxue', 2);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Wuli', 2);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Shengwu', 2);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Jiaotong', 3);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Tumu', 4);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Ruanjian', 5);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Jike', 6);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Chinese', 7);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Math', 8);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('English', 9);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Chinese', 10);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Lishi', 3);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('Dili', 3);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('ZZ', 4);
INSERT INTO `tag` (tag_name, post_id)
VALUES ('XG', 4);

INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'Why A?', 2, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'What A?', 3, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'How A?', 4, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'Why B?', 5, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'What B?', 6, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'How B?', 7, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'Why C?', 8, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'What C?', 9, now());
INSERT INTO `question` (post_id, content, questioner_id, time)
VALUES (1, 'Why D?', 10, now());

INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (1, 1, '233', 3, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (2, 1, '2333', 4, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (3, 1, '23333', 5, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (4, 1, 'ha', 6, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (5, 1, 'haha', 7, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (6, 1, 'hahaha', 8, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (7, 1, 'I DONT KNOW!!!!', 9, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (8, 1, 'wwwwww', 10, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (9, 1, 'wwwwwwwwww', 11, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (10, 1, 'wwwwwwwwwwwwwww', 12, now());

INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (11, 2, 'Dell', 4, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (12, 2, 'HP', 5, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (13, 3, 'Lenovo', 5, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (14, 3, 'Apple', 6, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (15, 4, 'Microsoft', 2, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (16, 4, 'Asus', 3, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (17, 5, 'Samsung', 4, now());
INSERT INTO `answer` (answer_id, question_id, content, answerer_id, time)
VALUES (18, 5, 'Huawei', 5, now());
