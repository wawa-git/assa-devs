# coding=utf8
import sys
import urllib2
import re
import time

from google.appengine.ext.remote_api import remote_api_stub
from google.appengine.ext import db
from wmp.models import Golf, GolfCourse

dbObjectsAccumulator = []
accumulator_size = 100

#-------------------------------------------------------------------------------
def find_tag( full_page , tag ):
    result = ""
    tag_name = ""
    tag_matches = re.compile( "<([^ ]+).*>" ).match( tag )
    if tag_matches:
        tag_name = tag_matches.group(1)
    str_regex = ".*" + tag + "(.*?)</" + tag_name + ">.*"
    regex = re.compile( str_regex , re.IGNORECASE | re.DOTALL )
    matches = regex.match( full_page )
    if matches:
        result = matches.group(1)
        result = result.replace( "\n" , "" )
        result = result.rstrip().lstrip()
    return result
    
#-------------------------------------------------------------------------------
def select_course_data( full_page ):
    result = ""
    regex = re.compile( ".*<table border=0 cellspacing=2>(.*)", re.IGNORECASE | re.DOTALL )
    matches = regex.match( full_page )
    if matches:
        result = matches.group(1)
        result = result.replace( "\n" , "" )
        result = result.rstrip().lstrip()
    return result


#-------------------------------------------------------------------------------
def get_golf_name( full_page ):
    name = find_tag( full_page , "<font color=\"#FFFFFF\" face=\"Arial\" size=\"3\">" )
    return unicode(name, "iso-8859-15")

#-------------------------------------------------------------------------------
def get_golf_address( full_page ):
    table_content = find_tag( full_page , "<TABLE BORDER=0 CELLSPACING=10 align=\"center\">" ) 
    regex = re.compile( ".*</td><TD><CENTER>([^<]+)<BR>([^<]+)<BR><span class=titrerouge>.*" , re.IGNORECASE )
    address = ""
    tel = ""
    matches = regex.match( table_content )
    if matches:
        address = unicode( matches.group(1) , "iso-8859-15")
        tels = matches.group(2)
        tel_matches = re.compile( ".*T.+l. : (.*) Fax.*" , re.IGNORECASE ).match( tels )
        if tel_matches :
            tel = unicode( tel_matches.group(1) , "iso-8859-15")
        #TODO: Handle tels
    return (address, tel)

#-------------------------------------------------------------------------------
def get_golf_course_name( full_page ):
    result = full_page 
    regex = re.compile( ".*<span class=titrerouge><B>([^<]+)</B></span>.*" , re.IGNORECASE | re.DOTALL )
    matches = regex.match( full_page )
    if matches:
        result = unicode( matches.group(1) , "iso-8859-15")
    return result

#-------------------------------------------------------------------------------
def parse_address( address ):
    regex = re.compile( "(.*)\\s*-\\s*(\\d+)\\s+(\\D+)" )
    street_address = ""
    postal_address = ""
    city_address = ""
    matches = regex.match( address )
    if matches:
        street_address = matches.group(1).rstrip().lstrip()
        postal_address = matches.group(2)
        city_address = matches.group(3)
    else:
        regex2 = re.compile( "\\s*(\\d+)\\s+(\\D+)" )
        matches = regex2.match( address )
        if matches:
            postal_address = matches.group(1)
            city_address = matches.group(2)
        else:
            print "Can't parse '" + address + "'"
    
    return ( street_address , postal_address , city_address )

     
#-------------------------------------------------------------------------------
def clean_page( data ):
    working_data = data
    working_data = re.compile( "<td[^>]*>" , re.IGNORECASE).sub( "<td>", working_data )
    working_data = re.compile( "</td[^>]*>" , re.IGNORECASE).sub( "</td>", working_data )
    
    working_data = re.compile( "<tr[^>]*>" , re.IGNORECASE).sub( "<tr>", working_data )
    working_data = re.compile( "</tr[^>]*>" , re.IGNORECASE).sub( "</tr>", working_data )

    working_data = re.compile( "</*center[^>]*>" , re.IGNORECASE).sub( "", working_data )
    working_data = re.compile( "</*font[^>]*>" , re.IGNORECASE).sub( "", working_data )
    working_data = re.compile( "</*b[^>]*>" , re.IGNORECASE).sub( "", working_data )
    return working_data

#-------------------------------------------------------------------------------
def parse_course( data , golf ):
    global dbObjectsAccumulator
    course_name = get_golf_course_name( data )
    print "\tGolf name: " + golf.name + "\tCourse name:" + course_name
    
    working_data = select_course_data(data)
    working_data = clean_page(working_data)
    working_data = working_data.replace("\n", "" )
    working_data = working_data.replace("</tr>", "</tr>\n" )
    regex = re.compile( ".*<tr>(.*)</tr>.*" )
    
    total_hole_number = 0
    pars = []
    hcps = []
    slope_male_black = -1
    slope_male_white = -1
    slope_male_yellow = -1
    slope_male_blue = -1
    slope_male_red = -1
    slope_female_yellow = -1
    slope_female_blue = -1
    slope_female_red = -1
    sss_male_black = -1.0
    sss_male_white = -1.0
    sss_male_yellow = -1.0
    sss_male_blue = -1.0
    sss_male_red = -1.0
    sss_female_yellow = -1.0
    sss_female_blue = -1.0
    sss_female_red = -1.0    
    distance_blacks = []
    distance_whites = []
    distance_yellows = []
    distance_blues = []
    distance_reds = []
    for line in working_data.splitlines():
        matches = regex.match( line )
        if matches:
            content = matches.group(1)
            regex2 = re.compile( ".*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*<td>(.*)</td>.*" )
            matches2 = regex2.match( content )
            
            if matches2:
                str_hole_number = matches2.group(1).rstrip().lstrip()
                try:
                    if str_hole_number.isdigit():
                        hole_number = int( str_hole_number )
                        par = int( matches2.group(2).rstrip().lstrip() )
                        str_hcp = matches2.group(3).rstrip().lstrip()
                        if str_hcp.isdigit() :
                            hcp = int( matches2.group(3).rstrip().lstrip() )
                        else:
                            hcp = -1
                        dist_black = int( matches2.group(4).rstrip().lstrip() )
                        dist_white = int( matches2.group(5).rstrip().lstrip() )
                        dist_yellow = int( matches2.group(6).rstrip().lstrip() )
                        dist_blue = int( matches2.group(7).rstrip().lstrip() )
                        dist_red = int( matches2.group(8).rstrip().lstrip() )
                        
                        total_hole_number += 1
                        print "\t  Hole:"+str(hole_number)+" par:"+str(par)+" hcp:"+str(hcp)+\
                        " black:"+str(dist_black)+\
                        " white:"+str(dist_white)+\
                        " yellow:"+str(dist_yellow)+\
                        " blue:"+str(dist_blue)+\
                        " red:"+str(dist_red)
                        pars.append(par)
                        hcps.append(hcp)
                        distance_blacks.append(dist_black)
                        distance_whites.append(dist_white)
                        distance_yellows.append(dist_yellow)
                        distance_blues.append(dist_blue)
                        distance_reds.append(dist_red)
                    else:
                        if "SSS Messieurs" == str_hole_number:
                            sss_male_black = float( matches2.group(4).rstrip().lstrip().replace(",",".") )
                            sss_male_white = float( matches2.group(5).rstrip().lstrip().replace(",",".") )
                            sss_male_yellow = float( matches2.group(6).rstrip().lstrip().replace(",",".") )
                            sss_male_blue = float( matches2.group(7).rstrip().lstrip().replace(",",".") )
                            sss_male_red = float( matches2.group(8).rstrip().lstrip().replace(",",".") )
                        elif "SSS Dames" == str_hole_number:
                            sss_female_yellow = float( matches2.group(6).rstrip().lstrip().replace(",",".") )
                            sss_female_blue = float( matches2.group(7).rstrip().lstrip().replace(",",".") )
                            sss_female_red = float( matches2.group(8).rstrip().lstrip().replace(",",".") )
                        elif "Slope Messieurs" == str_hole_number:
                            slope_male_black = int( matches2.group(4).rstrip().lstrip() )
                            slope_male_white = int( matches2.group(5).rstrip().lstrip() )
                            slope_male_yellow = int( matches2.group(6).rstrip().lstrip() )
                            slope_male_blue = int( matches2.group(7).rstrip().lstrip() )
                            slope_male_red = int( matches2.group(8).rstrip().lstrip() )
                        elif "Slope Dames" == str_hole_number:
                            slope_female_yellow = int( matches2.group(6).rstrip().lstrip() )
                            slope_female_blue = int( matches2.group(7).rstrip().lstrip() )
                            slope_female_red = int( matches2.group(8).rstrip().lstrip() )
                except Exception:
                    pass
                
    course = GolfCourse( golf=golf, name=course_name, hole_number=total_hole_number, \
                pars=pars, hcps=hcps, distance_blacks=distance_blacks, \
                distance_whites=distance_whites, distance_yellows=distance_yellows, \
                distance_blues=distance_blues, distance_reds=distance_reds ,\
                sss_male_black=sss_male_black, sss_male_white=sss_male_white, \
                sss_male_yellow=sss_male_yellow, sss_male_blue=sss_male_blue, \
                sss_male_red=sss_male_red, sss_female_yellow=sss_female_yellow, \
                sss_female_blue=sss_female_blue, sss_female_red=sss_female_red, \
                slope_male_black=slope_male_black, slope_male_white=slope_male_white, \
                slope_male_yellow=slope_male_yellow, slope_male_blue=slope_male_blue, \
                slope_male_red=slope_male_red, slope_female_yellow=slope_female_yellow, \
                slope_female_blue=slope_female_blue, slope_female_red=slope_female_red )
    dbObjectsAccumulator.append( course )
    #course.put()
    print "\tTotal: " + str(total_hole_number) + " holes"

#-------------------------------------------------------------------------------
def retrieve_course( golf_id , golf ):
    
    #TODO: optimize this for loop by retrieving course indice on the golf page
    for course_indice in range(1, 11):
        ffgolfcourse_url = "http://fleole.ffgolf.org/golf/terrain.htm?num_ter=0" +\
         str(course_indice) + "&num_golf=" + golf_id + "&st=1"
            
        #print "Requesting " + ffgolf_url
        req = urllib2.Request(ffgolfcourse_url)
        handle = urllib2.urlopen(req)
        golfpage = handle.read()
            
        if -1 == golfpage.find( "Terrain non trouv&eacute;" ):
            parse_course(golfpage, golf )


#-------------------------------------------------------------------------------
def parse_golf( data ):
    working_data = data.replace("\n", "" )
    name = get_golf_name(data) 
    address = ""
    if "FEDERATION FRANCAISE DE GOLF" != name:
        (address , tel) = get_golf_address( working_data )
        return (name, address, tel)
    else:
        return (None,None,None)


#-------------------------------------------------------------------------------
def retrieve_all():
    global dbObjectsAccumulator
    
    start_time = time.clock()
    range_start = 1
    range_end = 9999
    golf_list = {}
    for golf_number in range( range_start, range_end ):
        str_golf_number = str(golf_number)
        if len(str_golf_number) < 4:
            str_golf_number = "0" * (4 - len(str_golf_number)) + str_golf_number
            
        ffgolf_url = "http://fleole.ffgolf.org/golf/ficgol01.htm?num_golf=" + str_golf_number + "&st=0"
        try:
            req = urllib2.Request(ffgolf_url)
            golfpage = urllib2.urlopen(req).read()
            (golf_name, address, tel) = parse_golf( golfpage )
            if None != golf_name and "" != golf_name and None != address and "" != address:
                street_address, postal, city = parse_address( address )
                print str_golf_number + ": " + golf_name + " | " + street_address + " / " + postal + " / " + city
                dbGolf = Golf( id=golf_number, name=golf_name , street_address=street_address, \
                               postal_address=postal, city_address=city, phone=tel )
                #dbGolf.put()
                golf_list[ str_golf_number ] = dbGolf
            else:
                print str_golf_number + ": --"
        except:
            pass

    for golf_id, golf in golf_list.iteritems():
        dbObjectsAccumulator.append( golf )
        if accumulator_size < len(dbObjectsAccumulator):
            db.put( dbObjectsAccumulator )
            dbObjectsAccumulator = []

    db.put( dbObjectsAccumulator )
    for golf_id, golf in golf_list.iteritems():
        retrieve_course( golf_id , golf )
        if accumulator_size < len(dbObjectsAccumulator):
            db.put( dbObjectsAccumulator )
            dbObjectsAccumulator = []
        

    db.put( dbObjectsAccumulator )            
    end_time = time.clock()
    print "========================================================================="
    print "total time: " + str(end_time - start_time) + "s"
    print "avg site parsing: " + str( (end_time - start_time)/(range_end - range_start) ) + "sec/golf"



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
retrieve_all()
