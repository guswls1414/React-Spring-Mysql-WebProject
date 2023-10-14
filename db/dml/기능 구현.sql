-- 회원가입
insert into user values('guswls1414@gmail.com', 'qwer1234', 'hyun', '010-1234-5678', '경기도 용인시', '상현역', null);

-- 로그인
select * from user where email = 'guswls1414@gmail.com';

-- 게시물 작성
insert into board(title, content, write_datetime, favorite_count, comment_count, view_count, writer_email)
values('제목입니다.', '내용입니다.', '2023-08-21 11:32', 0, 0, 0, 'guswls1414@gmail.com');

insert into image values(1, 'image.jpg');

-- 댓글 작성
insert into comment(content, write_datetime, user_email, board_number)
values('안녕하세요', '2023-08-21 11:37', 'guswls1414@gmail.com', '1');

update board set comment_count = comment_count + 1 where board_number = 1;

-- 좋아요 기능
insert into favorite
values('guswls1414@gmail.com', 1);
update board set favorite_count = favorite_count + 1 where board_number = 1;

delete from favorite where user_email = 'guswls1414@gmail.com' and board_number = 1;
update board set favorite_count = favorite_count - 1 where board_number = 1;

-- 게시물 수정
update board set title='수정 제목입니다.', content='수정 내용입니다.' where board_number = 1;
delete from image where board_number = 1;
insert into image values(1, 'image.jpg');

-- 게시물 삭제
delete from comment where board_number = 1;
delete from favorite where board_number = 1;
delete from image where board_number = 1;
delete from board where board_number = 1;

-- 상세 게시물 불러오기
select 
	B.board_number as board_number,
	B.title as title,
	B.content as content,
	B.write_datetime as write_datetime,
	B.writer_email as writer_email,
	U.nickname as nickname,
	U.profile_image as profile_image
from board as B
inner join user as U
on B.writer_email = U.email
where board_number = 1;

select image from image where board_number = 1;

select 
	U.email as email,
    U.nickname as nickname,
    U.profile_image as profile_image
from favorite as F
inner join user as U
on F.user_email = U.email
where F.board_number = 1;

select 
	U.nickname as nickname,
    U.profile_image as profile_image,
    C.write_datetime as write_datetime,
    C.content as content
from comment as C
inner join user as U
on C.user_email = U.email
where C.board_number = 1
order by write_datetime desc;

-- 최신 게시물 리스트 불러오기
select * from board_list_view
order by write_datetime desc
limit 5, 5;

-- 검색어 리스트
select * from board_list_view
where title like '%수정%' or content like '%제목%'
order by write_datetime desc;

-- 주간 상위3 리스트 불러오기
select * from board_list_view
where write_datetime between '2023-08-15 15:11' and '2023-08-21 15:11'
order by favorite_count desc, comment_count desc, view_count desc, write_datetime desc
limit 3;

-- 특정 유저 게시물 리스트 불러오기
select * from board_list_view
where writer_email = 'guswls1414@gmail.com'
order by write_datetime desc;

-- 인기 검색어 리스트 불러오기
select search_word, count(search_word) as count
from search_log
where relation is false
group by search_word
order by count desc
limit 15;

-- 연관 검색어 리스트
select relation_word, count(relation_word) as count
from search_log
where search_word = '검색어'
group by relation_word
order by count desc
limit 15;

-- 유저 정보 불러오기 / 로그인 유저 정보 불러오기
select *
from user
where email = 'guswls1414@gmail.com';

-- 닉네임 수정
update user set nickname = '수정 닉네임'
where email = 'guswls1414@gmail.com';

-- 프로필 이미지 수정
update user set profile_image = 'url2'
where email = 'guswls1414@gmail.com';



select * from board;
select * from comment;
select * from favorite;
select * from image;
select * from search_log;