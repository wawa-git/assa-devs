# coding=utf8
from google.appengine.ext.webapp import RequestHandler

from wmp import engine
from wmp import auth
from wmp.models import UserGolfEntry

#===================================================================================
class Handler(RequestHandler):
    #-------------------------------------------------------------------------------
    def get(self):
        user = auth.identify_user_by_cookie(self)
        args = {}
        if user:
            args["entries"] = UserGolfEntry.gql("WHERE user = :1", user)
        page = engine.page(self, args)
        self.response.out.write(page)
