# coding=utf8
import logging

from google.appengine.ext import webapp

from wmp import engine
from wmp import auth
from wmp.models import User

#===================================================================================
class Handler( webapp.RequestHandler ):
    
    #-------------------------------------------------------------------------------
    def get(self):
        page = engine.page(self, {})
        self.response.out.write(page)

    #-------------------------------------------------------------------------------
    def post(self):
        arguments = engine.args(self)
        target = "edit_user_info"
        try:
            pseudo = arguments["pseudo"]
            email = arguments["email"]
            password = arguments["password"]
            password2 = arguments["password2"]

            if( password == password2 ):
                user = User(pseudo=pseudo, email=email, password=password)
                user.put()
            auth.set_cookie(self, user, False)

        except Exception,e:
            logging.warning( "Failed to register user . Cause by: '" + str(e) + "'" )
        
        self.redirect( target )
