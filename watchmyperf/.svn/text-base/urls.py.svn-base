# coding=utf8
from wmp import home
from wmp import register
from wmp import edit_user_info
from wmp import do_login
from wmp import do_logout
from wmp.golf import add
from wmp.golf import stats
from wmp.golf import courses
from wmp.golf import historic

# URL -> Handler mapping
# Warning: First parameter is a regex
handlers = [
            ('/golf/add', add.Handler),
            ('/golf/stats', stats.Handler),
            ('/golf/historic', historic.Handler),
            ('/golf/golf_list.js', courses.Handler),
            ('/register.*', register.Handler),
            ('/edit_user_info.*', edit_user_info.Handler),
            ('/do_login.*', do_login.Handler),
            ('/logout.*', do_logout.Handler),
            ('/.*', home.Handler),
            ]
