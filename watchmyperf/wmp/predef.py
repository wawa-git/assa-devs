# coding=utf8
# Predef is the file always included by all python file
# Inside is define all function for language extension


#-------------------------------------------------------------------------------
def get_or_else( container , key , default ):
    value = map.get( key , "#$%%" )
    if "" == value or "#$%%" == value:
        value = default
    return value

