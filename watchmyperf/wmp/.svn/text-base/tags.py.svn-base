# coding=utf8
from google.appengine.ext import webapp
register = webapp.template.create_template_register()

#-------------------------------------------------------------------------------
def is_current( current, sport ):
    result = str(sport)
    if str(sport) == str(current) :
        result += " current"
    
    return result

register.filter(is_current)

#-------------------------------------------------------------------------------
def submenu(page_name, link_and_descr):
    tmp = link_and_descr.split("\\")
    link = tmp[0]
    description = tmp[1]
    
    selection = ""
    if link == page_name:
        selection = 'class="current"'
        
    return """<li %s><a href="%s">%s</a></li>""" % (selection, link, description)
register.filter(submenu)

#-------------------------------------------------------------------------------
def force_sign(number):
    if 0 < number:
        return "+" + str(number)
    else:
        return str(number)
register.filter(force_sign)

