# coding=utf8
import os

from google.appengine.ext.webapp import template

from facebook import fb_key
from wmp import auth
import settings

#-------------------------------------------------------------------------------
def get_browser(request_handler):
    known_browsers = [ "msie", "firefox", "chrome", "safari" ]
    current_browser = known_browsers[0]
    if "HTTP_USER_AGENT" in request_handler.request.environ:
        user_agent = request_handler.request.environ["HTTP_USER_AGENT"].lower()
        for browser in known_browsers:
            if -1 != user_agent.find( browser ):
                current_browser = browser
                break
    return current_browser

#-------------------------------------------------------------------------------
def _get_sport(path):
    arguments = path[1:].split( "/" )
    sport = None
    if 0 < len(arguments) and "" != arguments[0] and arguments[0] in ["golf", "cycling", "trekking", "running"]:
        sport = arguments[0]
    return sport

#-------------------------------------------------------------------------------
def _get_page_name(path):
    arguments = path[1:].split( "/" )
    name = ""
    if 0 < len(arguments) and "" != arguments[0] and arguments[0] not in ["golf", "cycling", "trekking", "running"]:
        name = arguments[0]
    elif 1 < len(arguments):
        name = arguments[1]
    
    if name == "":
        name = "home"
    return name


#-------------------------------------------------------------------------------
def args(request_handler):
    arguments = {}
    dic = request_handler.request.params
    for k in dic.keys():
        arguments[k] = dic.get(k)
    arguments["browser"] = get_browser(request_handler)
        
    return arguments


#-------------------------------------------------------------------------------
def generate_sport_template( sport , page_name , template_arguments , default = "" ):
    result = None
    try:
        content_template_dir = settings.TEMPLATE_DIRS
        if sport:
            content_template_dir = os.path.join(settings.TEMPLATE_DIRS, sport)
        content_template_name = os.path.join(content_template_dir, page_name + ".html")
        result = template.render(content_template_name, template_arguments)
    except:
        result = default
        
    return result

#-------------------------------------------------------------------------------
def get_deploy_datetime():
    deploy_date = None
    try:
        deploy_date_name = os.path.join(settings.ROOT_PATH, "deploy_date.dat")
        deploy_date = template.render(deploy_date_name, {})
    except:
        deploy_date = ""

    deploy_time = None
    try:
        deploy_time_name = os.path.join(settings.ROOT_PATH, "deploy_time.dat")
        deploy_time = template.render(deploy_time_name, {})
    except:
        deploy_time = ""
    
    return deploy_date, deploy_time

#-------------------------------------------------------------------------------
def page(request_handler, additional_args = {}):
    path = request_handler.request.path
    sport = _get_sport(path)
    name = _get_page_name(path)
    return full_page(request_handler, sport, name, additional_args)

#-------------------------------------------------------------------------------
def full_page(request_handler, sport, name, additional_args = {}):
    arguments = args(request_handler)
    
    arguments["fb_api_key"] = fb_key.api_key()
    arguments["user"] = auth.identify_user_by_cookie(request_handler)

    # add additionnal args after setting user from cookie, in order to force
    # the value of the user
    for k,v in additional_args.iteritems():
        arguments[k] = v
        
    arguments["sport"] = sport
    arguments["page_name"] = name
    
    deploy_date, deploy_time = get_deploy_datetime()
    arguments["deploy_date"] = deploy_date
    arguments["deploy_time"] = deploy_time
    
    submenu = generate_sport_template( arguments["sport"] , "submenu" , arguments , "" )
    arguments["submenu"] = submenu
    
    content = generate_sport_template( arguments["sport"] , arguments["page_name"] , arguments , \
                                    """La page "%s" n'existe pas encore""" % (arguments["page_name"]) )
    arguments["content"] = content
    
    #-------------------
    page = generate_sport_template( None , "index" , arguments , "" )
    
    return page
