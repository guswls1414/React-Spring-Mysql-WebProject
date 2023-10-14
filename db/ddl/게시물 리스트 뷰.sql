create view board_list_view as 
select 
B.board_number as board_number,
B.title as title,
B.content as content,
I.image as title_image,
B.favorite_count as favorite_count,
B.comment_count as comment_count,
B.view_count as view_count,
B.write_datetime as write_datetime,
B.writer_email as writer_email,
U.nickname as writer_nickname,
U.profile_image as writer_profile_image
from board as B
inner join user as U
on B.writer_email = U.email
left join (select board_number, any_value(image) as image from image group by board_number) as I
on B.board_number = I.board_number;