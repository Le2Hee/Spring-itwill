create table posts (
    id              number(8) constraint posts_id_pk primary key,
    title           varchar2(100 char) constraint posts_title_nn not null,
    content         varchar2(1000 char) constraint posts_content_nn not null,
    author          varchar2(20 char) constraint posts_author_nn not null,
    created_time     timestamp default sysdate,
    modified_time    timestamp default sysdate
);

insert into posts(title, content, author)
values ('Lamborghini Urus S', '228,964.83$', 'LAMBORGHINI');

delete from posts;

select * from members;

commit;

insert into members (username, password, email)
values ('guest', 'guest', 'guest@itwill.co.kr');

alter table members add constraint MEMBERS_USERNAME_UQ unique(username);

insert into replies (post_id, reply_text, writer)
values (46, 'rrrqqqq', 'admin');

select * from replies where post_id = 46;

alter table replies
add constraint replies_post_id_fk foreign key(post_id) references posts(id);

select count(*) from replies where post_id = 46;




