# coding=utf8

user = "user_key"

#-------------------------------------------------------------------------------
def get(request_handler, name, default):
    return request_handler.request.cookies.get(name , default)

#-------------------------------------------------------------------------------
def set(response, key, value, expires):
    header = key + "=" + value + ";"
    if None != expires:
        header += " expires=" + expires
        
    response.headers.add_header("Set-Cookie", header)

