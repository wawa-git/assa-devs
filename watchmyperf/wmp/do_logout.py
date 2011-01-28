# coding=utf8
from google.appengine.ext import webapp
from facebook import fb_key
from wmp import cookie

#===================================================================================
class Handler( webapp.RequestHandler ):

    def delete_cookie(self, name):
        self.response.headers.add_header( "Set-Cookie", name+"=''; expires='Thu, 01-Jan-1970 00:00:00 GMT'" )

    #-------------------------------------------------------------------------------
    def get(self):
        fb_api_key = fb_key.api_key()
        self.delete_cookie( cookie.user )
        self.delete_cookie( fb_api_key )
        self.delete_cookie( fb_api_key + "_expires" )
        self.delete_cookie( fb_api_key + "_session_key" )
        self.delete_cookie( fb_api_key + "_ss" )
        self.delete_cookie( fb_api_key + "_user" )
        
        self.redirect( "/" )

