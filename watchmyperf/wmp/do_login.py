# coding=utf8
import cgi
import md5
import logging

from google.appengine.ext import webapp

from wmp.models import User
from wmp import engine
from wmp import cookie
from wmp import auth
 
from facebook import fb_key
from facebook.facebook import Facebook

#===================================================================================
class Handler( webapp.RequestHandler ):

    #-------------------------------------------------------------------------------
    def register_user_by_fbuid(self, user_uid):
        fb = Facebook(fb_key.api_key(), fb_key.secret())
        info = fb.users.getInfo([user_uid], ['first_name', 'last_name', 'name', 'sex'])[0]
        new_user = User( pseudo=info['name'] )
        new_user.facebook_uid = user_uid
        new_user.firstname = info['first_name']
        new_user.lastname = info['last_name']
        if "Female" == info['sex']:
            new_user.gender = "female"
        else:
            new_user.gender = "male"
        new_user.put()
        return new_user
    
    #-------------------------------------------------------------------------------
    def get(self):
        template_page_name = "home"
        sport = None
        args = engine.args(self)
        additional_args = {}
        try:
            if "goto" in args:
                path = args["goto"]
                template_page_name = engine._get_page_name(path)
                sport = engine._get_sport(path)
                
            email = cgi.escape( self.request.get( 'email' , "" ) )
            remember_me = False
            identified_user = None
            if "" != email:
                password = cgi.escape( self.request.get( 'password' ) )
                remember_me = ("on" == cgi.escape(self.request.get( 'remember_me' , 'off' )) )
                hash_password = md5.new( password ).hexdigest()
                identified_user = auth.identify_user_by_email(email, hash_password)
                
            else:
                str_user_fbuid = cookie.get( self , fb_key.api_key() + "_user" , None )
                user_fbuid = int(str_user_fbuid)
                identified_user = auth.identify_user_by_fbuid(user_fbuid)
                if not identified_user:
                    identified_user = self.register_user_by_fbuid(user_fbuid)
                    sport = None
                    template_page_name = "edit_user_info"
                    
            if identified_user:
                auth.set_cookie(self, identified_user, remember_me)
                additional_args["user"] = identified_user
            else:
                sport = None
                template_page_name = "login"
                additional_args["error"] = "Login/Password invalide !"
                
        except Exception,e:
            logging.warning( "User failed to loggin (Cause by: '" + str(e) + "')" )

        target = "/"
        if sport:
            target += sport + "/"
        if "home" != template_page_name:
            target += template_page_name
        self.redirect( target )

