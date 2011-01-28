# coding=utf8
from google.appengine.ext.webapp import RequestHandler

from wmp import engine

#===================================================================================
class Handler(RequestHandler):
    #-------------------------------------------------------------------------------
    def get(self):
        page = engine.page(self)
        self.response.out.write(page)
