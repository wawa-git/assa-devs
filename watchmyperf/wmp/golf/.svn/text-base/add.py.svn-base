# coding=utf8
import logging
from google.appengine.ext import db
from google.appengine.ext.webapp import RequestHandler

from datetime import date

from wmp import engine
from wmp import auth
from wmp.models import GolfCourse, UserGolfEntry

#===================================================================================
class Handler(RequestHandler):
    #-------------------------------------------------------------------------------
    def get(self):
        page = engine.page(self)
        self.response.out.write(page)

    #-------------------------------------------------------------------------------
    def post(self):
        arguments = engine.args(self)
        
        def is_checked( name ):
            return ("on" == self.request.get(name, "off")) 
        
        try:
            golf_key = arguments["golf_course"]
            golf_course = GolfCourse.get(golf_key)
            str_course_date = arguments["course_date"].split("/")
            course_date = date( int(str_course_date[2]), int(str_course_date[1]), int(str_course_date[0]) )
            user = auth.identify_user_by_cookie(self)

            entry = UserGolfEntry( user=user , golf_course=golf_course, date=course_date )

            for i in range(0, golf_course.hole_number ):
                entry.pars = golf_course.pars
                entry.fairways.append( is_checked("fwy" + str(i)) )
                entry.girs.append( is_checked("gir" + str(i)) )
                entry.puts.append( int(self.request.get("puts" + str(i), -1)) )
                entry.scores.append( int(self.request.get("score" + str(i), -1)) )
                
                # optional
                if is_checked("advanced_mode"):
                    fc = int(self.request.get("first_club" + str(i), 0))
                    entry.first_club.append( fc )
                    lc = int(self.request.get("last_club" + str(i), 0))
                    entry.last_club.append( lc )
            user.golf_entry_nb += 1
            
            db.put( [entry, user] )
            self.redirect("stats")
            
        except Exception,e:
            msg = "Failed to add user entry. Cause by: '%s'" % (str(e))
            logging.warning( msg )
            page = engine.full_page(self, "golf", "add", {"error":msg})
            self.response.out.write(page)
