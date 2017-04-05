UPDATE t_activity SET logo=REPLACE(logo, 'http://127.0.0.1:8093', 'http://127.0.0.1:8010'); 
UPDATE t_investor SET photo=REPLACE(photo, 'http://127.0.0.1:8093', 'http://127.0.0.1:8010'); 
UPDATE t_project SET logo=REPLACE(logo, 'http://127.0.0.1:8093', 'http://127.0.0.1:8010'); 
UPDATE t_team_member SET photo=REPLACE(photo, 'http://127.0.0.1:8093', 'http://127.0.0.1:8010'); 
UPDATE t_user SET photo=REPLACE(photo, 'http://127.0.0.1:8093', 'http://127.0.0.1:8010');