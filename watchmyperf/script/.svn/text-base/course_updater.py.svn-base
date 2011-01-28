# coding=utf8
import sys

from google.appengine.ext.remote_api import remote_api_stub
from wmp.models import Golf, GolfCourse, Page

#-------------------------------------------------------------------------------
def mkstring(container , separator ):
    result = ""
    for elem in container:
        result += str(elem) + separator
    if "" != result:
        result = result[:-1]
    return result


#-------------------------------------------------------------------------------
def generate_js_golf_list():
    print( "Start generate_js_golf_list" )
    golf_courses = {}
    for golf_course in GolfCourse.all().fetch(1000):
        print( "Handling Golf Course " + golf_course.name + "\n" )
        key = str(golf_course.golf.key())
        if not golf_courses.has_key(key):
            golf_courses[key] = []
        golf_courses[key].append( golf_course )
    
    golf_names = "var js_golf_list = [\n"
    for golf in Golf.all().fetch(1000):
        print( "Handling Golf " + golf.name + "\n" )
        str_golf_courses = "[\n"
        key = str(golf.key())
        if golf_courses.has_key(key):
            for golf_course in golf_courses[key]:
                str_golf_courses += "{\n"
                str_golf_courses += "key:\"" + str(golf_course.key()) + "\",\n"
                str_golf_courses += "name:\"" + golf_course.name + "\",\n"
                str_golf_courses += "hole_number:\"" + str(golf_course.hole_number) + "\",\n"
                str_golf_courses += "pars:\"" + mkstring(golf_course.pars, ",") + "\",\n"
                str_golf_courses += "distance_blacks:\"" + mkstring(golf_course.distance_blacks, ",") + "\",\n"
                str_golf_courses += "distance_whites:\"" + mkstring(golf_course.distance_whites, ",") + "\",\n"
                str_golf_courses += "distance_yellows:\"" + mkstring(golf_course.distance_yellows, ",") + "\",\n"
                str_golf_courses += "distance_blues:\"" + mkstring(golf_course.distance_blues, ",") + "\",\n"
                str_golf_courses += "distance_reds:\"" + mkstring(golf_course.distance_reds, ",") + "\"\n"
                str_golf_courses += "},\n"
            str_golf_courses = str_golf_courses[:-1] + "]\n"
        else:
            str_golf_courses += "{name:\"NC\",key:\"\"}]\n"
        
        golf_names += "{name:\"" + golf.name + "\",key:\"" + str(golf.key()) + \
        "\",courses:" + str_golf_courses + "},\n"
    if "[" != golf_names:
        golf_names = golf_names[:-1] + "];\n"
        
    return unicode(golf_names)

#-------------------------------------------------------------------------------
def update_courses_list():
    golf_list = generate_js_golf_list()
    page = Page( name="golf_list" , data=golf_list )
    page.put()

if len(sys.argv) < 4:
    print "Usage: %s <app_id> <host> <login> <password>" % (sys.argv[0])
    sys.exit(0)

app_id = sys.argv[1]
host = sys.argv[2]
login = sys.argv[3]
password = sys.argv[4]

def auth_func():
    return login, password

remote_api_stub.ConfigureRemoteDatastore(app_id, '/remote_api', auth_func, host)
update_courses_list()
