#用户共有属性表
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    phone  VARCHAR(20) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#用户详情表（一般为不常用数据）
DROP TABLE IF EXISTS user_detail;
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
#会员表，当用户包名训练营时加入信息
DROP TABLE IF EXISTS member;
CREATE TABLE member(
    id BIGINT(20) NOT NULL, #与用户id相匹配
    vip INT(11) DEFAULT 0, #剩余vip时间，为0则表示非vip
    reference_id BIGINT(20),#推荐人id，null表示无

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#导师信息表
DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher(
    id BIGINT(20) NOT NULL, #与用户id相匹配
    star INT(11) NOT NULL DEFAULT 1, #星级
    account TEXT NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#训练营信息表
DROP TABLE IF EXISTS camp;
CREATE TABLE camp (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    type INT(11) DEFAULT 0, #0 家长创建， 1 专项， 2 机构
    favor INT(11) DEFAULT 0,#点赞数
    description TEXT,#导师说
    comment TEXT,#平台点评
    max_limit INT(11) DEFAULT 0,#最大人数限制
    min_limit INT(11) DEFAULT 0,#最小人数限制
    status INT(11) DEFAULT 0, #0 初始, 1 上线, 2 开课, 3 下线
    price0 DECIMAL(10,2),
    price1 DECIMAL(10,2),
    price2 DECIMAL(10,2),
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
#储存会员加入的训练营
DROP TABLE IF EXISTS camp_attend;
CREATE TABLE camp_attend(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    camp_id BIGINT(20) NOT NULL,
    expiration TIMESTAMP NOT NULL, #训练营到期时间
    role TINYINT(1) DEFAULT 0, #会员在训练营中的身份 0 普通成员, 1 管理员, 2 导师, 3 观察员, 4 编制外成员

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 储存用户收藏的训练营 */
DROP TABLE IF EXISTS camp_collection;
CREATE TABLE camp_collection(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    camp_id BIGINT(20) NOT NULL,
    
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#话题 */
DROP TABLE IF EXISTS topic;
CREATE TABLE topic(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    originator_id BIGINT(20) NOT NULL,/* 发起者 id*/
    title VARCHAR(100) NOT NULL,
    type INT(11) NOT NULL, /* 0 公开群聊话题, 1 临时1v1, 2 持久1v1*/
    sticky BOOLEAN DEFAULT FALSE, /* 是否精华 */
    camp_id BIGINT(20) DEFAULT NULL, /* 话题归属， NULL 表示不属于群聊 */

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS topic_content;
CREATE TABLE topic_content(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    speaker_id BIGINT(20) NOT NULL,
    content TEXT NOT NULL,
    time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 储存用户参与的话题 */
DROP TABLE IF EXISTS topic_involve;
CREATE TABLE topic_involve(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    topic_id BIGINT(20) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 储存用户关注的话题 */
DROP TABLE IF EXISTS topic_collection;
CREATE TABLE topic_collection(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    topic_id BIGINT(20) NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS balance;
CREATE TABLE balance(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    type BIGINT(20) NOT NULL, /* -1 表示购买vip, 其余与训练营id配对表示为加入训练营付费 */
    time TIMESTAMP NOT NULL,

    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS coupon;
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
DROP TABLE IF EXISTS tag;
CREATE TABLE tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;

/* tag 与目标id对应表 */
DROP TABLE IF EXISTS user_tag;
CREATE TABLE user_tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL ,
    tag_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS camp_tag;
CREATE TABLE camp_tag(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    camp_id BIGINT(20) NOT NULL ,
    tag_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS role;
CREATE TABLE role(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(20) NOT NULL ,
    role_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS school;
CREATE TABLE school(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    full_name TEXT NOT NULL ,
    heat INT(11) NOT NULL DEFAULT 0,
    location_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;

DROP TABLE IF EXISTS location;
CREATE TABLE location(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    province TEXT,
    city TEXT NOT NULL ,
    district TEXT NOT NULL ,

    PRIMARY KEY (id)
) ENGINE =InnoDB DEFAULT CHARSET =utf8;

#优惠券模板表
DROP TABLE IF EXISTS coupon_base;
CREATE TABLE coupon_base(
    id INT(11) NOT NULL AUTO_INCREMENT,
    amount DECIMAL(10,2) NOT NULL ,
    duration INT(11) NOT NULL DEFAULT 30,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

#优惠券发放策略表
#channel 表示策略针对哪个渠道的注册用户
#base_id 表示优惠券的模板的id
#number 表示发放多少张该base_id的优惠券
DROP TABLE IF EXISTS coupon_policy;
CREATE TABLE coupon_policy(
    id INT(11) NOT NULL AUTO_INCREMENT,
    channel INT(11) NOT NULL ,
    base_id INT(11) NOT NULL ,
    number INT(11) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;

#学校别名表
DROP TABLE IF EXISTS school_alias;
CREATE TABLE school_alias(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    alias TEXT NOT NULL ,
    school_id BIGINT(20) NOT NULL ,

    PRIMARY KEY (id)
)ENGINE =InnoDB DEFAULT CHARSET =utf8;
