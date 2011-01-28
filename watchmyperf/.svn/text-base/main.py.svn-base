"""

Bootstrap for starting appengine

"""

# Standard Python imports.
import os
import sys

# Google App Engine imports.
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app

# wmp imports.
import settings
import urls

#-----------------------------------------------------------------------------------
def main():
    sys.path.append( settings.ROOT_PATH )
    wmp_path = os.path.join(settings.ROOT_PATH, "wmp")
    sys.path.append( wmp_path )
    
    application = webapp.WSGIApplication( urls.handlers , settings.DEBUG )
    webapp.template.register_template_library( "wmp.tags" )
    run_wsgi_app( application )

#-----------------------------------------------------------------------------------
if __name__ == '__main__':
    main()
