create table bill
(
    order_id varchar(32)       not null
        primary key,
    amount   decimal default 0 null
)
    comment '订单表';

create table crawler_content
(
    id              varchar(32)  not null
        primary key,
    publish_time    varchar(8)   null comment '新闻发布时间',
    publish_content varchar(256) null comment '新闻发布内容
',
    crawler_date    varchar(16)  null comment '爬取日期',
    crawler_time    varchar(16)  null comment '爬取时间'
)
    comment '爬虫内容表';

create index crawler_date__index
    on crawler_content (crawler_date);

create table product
(
    id           varchar(32) not null
        primary key,
    product_name varchar(32) null
)
    comment '产品表';

