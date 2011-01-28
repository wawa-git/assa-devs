# coding=utf8
import datetime
import logging

from wmp.models import User
from wmp import cookie

#-------------------------------------------------------------------------------
def identify_user_by_fbuid( user_uid ):
    identified_user = None
    for user in User.gql( "WHERE facebook_uid=:1" , user_uid ) :
        identified_user = user
        break

    return identified_user

#-------------------------------------------------------------------------------
def identify_user_by_email(email, password):
    identified_user = None
    for user in User.gql( "WHERE email=:1" , email ) :
        if password == user.password:
            identified_user = user
            break
    return identified_user

#-------------------------------------------------------------------------------
def identify_user_by_key( key ):
    user = None
    if key:
        try:
            user = User.get( key )
        except Exception,e:
            logging.warning( "Identify user by key failed (Cause by: '" + str(e) + "')" )

    return user

#-------------------------------------------------------------------------------
def identify_user_by_cookie( request_handler ):
    key = cookie.get(request_handler, cookie.user, None)
    return identify_user_by_key(key)
    
#-------------------------------------------------------------------------------
def set_cookie(request_handler, user, remember_me):
    if remember_me:
        two_weeks = datetime.datetime.utcnow() + datetime.timedelta(days=28)
        expires = two_weeks.strftime("%a %d-%b-%y %H:%M:%S GMT")
        cookie.set( request_handler.response, cookie.user , str(user.key()) , expires )
    else:
        cookie.set( request_handler.response, cookie.user , str(user.key()) , None )
