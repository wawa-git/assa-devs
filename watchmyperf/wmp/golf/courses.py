# coding=utf8
import datetime

from google.appengine.ext.webapp import RequestHandler

from wmp.models import Page


#===================================================================================
class Handler(RequestHandler):
    #-------------------------------------------------------------------------------
    def get(self):
        golf_list = ""
        pages = Page.gql( "WHERE name = :1", "golf_list" )
        for page in pages:
            golf_list = page.data
            break
        
        expires_date = datetime.datetime.utcnow() + datetime.timedelta(7)
        expires_str = expires_date.strftime("%d %b %Y %H:%M:%S GMT")
        self.response.headers.add_header("Expires", expires_str)
        self.response.out.write(golf_list)
