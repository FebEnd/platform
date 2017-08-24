DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_detail;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS camp;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS camp_attend;
DROP TABLE IF EXISTS camp_collection;
DROP TABLE IF EXISTS topic_involve;
DROP TABLE IF EXISTS topic_content;
DROP TABLE IF EXISTS topic_collection;
DROP TABLE IF EXISTS balance;
DROP TABLE IF EXISTS coupon;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS user_tag;
DROP TABLE IF EXISTS camp_tag;
DROP TABLE IF EXISTS tag_map;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS school;
DROP TABLE IF EXISTS location;
/* 用户 公有 */
CREATE TABLE user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    phone  VARCHAR(20) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,


    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_detail(
    id BIGINT(20) NOT NULL ,
    city VARCHAR(50),
    live_district VARCHAR(50),
    target_district VARCHAR(50),
    child_birth DATE,
    child_grade VARCHAR(20),
    child_gender VARCHAR(10),
    child_school VARCHAR(100),

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;

/* 会员表，当用户报名课程时加入信息 */
CREATE TABLE member(
    id BIGINT(20) NOT NULL, /*与用户id匹配 */
    vip BIGINT(20) DEFAULT 0, /* 剩余vip时间, 为0则非vip */
    reference_id BIGINT(20),#推荐人id，null表示无

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE teacher(
    id BIGINT(20) NOT NULL, /* 与用户id匹配 */
    star INT(11) NOT NULL DEFAULT 1, #星级
    account TEXT NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE camp (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    type INT(11) DEFAULT 0, /*0 家长创建， 1 专项， 2 机构*/
    favor INT(11) DEFAULT 0,/*点赞数*/
    max_limit INT(11) DEFAULT 0,/*最大人数限制*/
    min_limit INT(11) DEFAULT 0,/*最小人数限制*/
    status INT(11) DEFAULT 0, /*0 初始, 1 上线, 2 开课, 3 下线*/
    price0 DECIMAL(10,2),
    price1 DECIMAL(10,2),
    price2 DECIMAL(10,2),
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 储存会员加入的训练营 */
CREATE TABLE camp_attend(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    camp_id BIGINT(20) NOT NULL,
    expiration TIMESTAMP NOT NULL, /* 训练营到期时间 */
    role TINYINT(1) DEFAULT 0, /* 会员在训练营中的身份 0 普通成员, 1 管理员, 2 导师, 3 观察员, 4 编制外成员 */

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/* 储存用户收藏的训练营 */
CREATE TABLE camp_collection(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    camp_id BIGINT(20) NOT NULL,
    
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 话题 */
CREATE TABLE topic(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    originator_id BIGINT(20) NOT NULL,/* 发起者 id*/
    title VARCHAR(100) NOT NULL,
    type INT(11) NOT NULL, /* 0 公开群聊话题, 1 临时1v1, 2 持久1v1*/
    sticky BOOLEAN DEFAULT FALSE, /* 是否精华 */
    camp_id BIGINT(20) DEFAULT NULL, /* 话题归属， NULL 表示不属于群聊 */

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE topic_content(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    speaker_id BIGINT(20) NOT NULL,
    content TEXT NOT NULL,
    time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 储存用户参与的话题 */
CREATE TABLE topic_involve(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    topic_id BIGINT(20) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 储存用户关注的话题 */
CREATE TABLE topic_collection(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    topic_id BIGINT(20) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE balance(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    type BIGINT(20) NOT NULL, /* -1 表示购买vip, 其余与训练营id配对表示为加入训练营付费 */
    time TIMESTAMP NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE coupon(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL ,
    channel BIGINT(20) NOT NULL ,#渠道id，与用户id相匹配，再设定渠道的具体id
    name VARCHAR(50) NOT NULL ,
    description TEXT ,
    amount DECIMAL(10,2) NOT NULL ,
    expiration TIMESTAMP NOT NULL ,
    publish TIMESTAMP NOT NULL ,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET =utf8;
/* tag 存储表 */
CREATE TABLE tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;
/* tag 与目标id对应表 */
CREATE TABLE user_tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL ,
    tag_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;
CREATE TABLE camp_tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    camp_id BIGINT(20) NOT NULL ,
    tag_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;
CREATE TABLE role(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;
CREATE TABLE user_role(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL ,
    role_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;
CREATE TABLE school(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    full_name TEXT NOT NULL ,
    alias TEXT,
    location_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;
CREATE TABLE location(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    province TEXT,
    city TEXT NOT NULL ,
    district TEXT NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;
