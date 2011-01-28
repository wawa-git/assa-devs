# coding=utf8
import logging

from google.appengine.ext import webapp

from wmp import engine
from wmp import auth

#===================================================================================
class Handler( webapp.RequestHandler ):

    #-------------------------------------------------------------------------------
    def display(self):
        page = engine.page(self, {})
        self.response.out.write(page)
    
    #-------------------------------------------------------------------------------
    def get(self):
        self.display()

    #-------------------------------------------------------------------------------
    def post(self):
        arguments = engine.args(self)
        user = auth.identify_user_by_cookie(self)
        try:
            def get_args(name, default=""):
                res = default
                try:
                    res = arguments[name]
                except:
                    pass
                return res
                    
            user.pseudo = get_args("pseudo")
            user.firstname = get_args("firstname")

            user.lastname = get_args("lastname")
            user.gender = get_args("gender", "male")
            user.email = get_args("email")
            user.golf_licence = get_args("golf_licence")
            user.cycling_licence = get_args("cycling_licence")
            user.trekking_licence = get_args("trekking_licence")
            user.running_licence = get_args("running_licence")
            
            user.put()
            
        except Exception,e:
            logging.warning( "Failed to edit user info. Cause by: '" + str(e) + "'" )

        self.display()
