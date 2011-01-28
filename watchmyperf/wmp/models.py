# coding=utf8
from google.appengine.ext import db

#===================================================================================
class Page( db.Model ):
    name = db.StringProperty( required=True )
    data = db.TextProperty( required=True )

#===================================================================================
class User( db.Model ):
    # user minimal data
    pseudo = db.StringProperty( required=True )
    firstname = db.StringProperty( default=None )
    lastname = db.StringProperty( default=None )
    gender = db.StringProperty( default=None, choices=set(["male", "female"]) )

    # for on-site authentification
    email = db.EmailProperty( default=None )
    password = db.StringProperty( default=None )
    
    # for facebook authentification
    facebook_uid = db.IntegerProperty( default=None )

    # stat data
    golf_entry_nb = db.IntegerProperty( default=0 )
    cycling_entry_nb = db.IntegerProperty( default=0 )
    running_entry_nb = db.IntegerProperty( default=0 )
    trekking_entry_nb = db.IntegerProperty( default=0 )

    # golf data
    golf_licence = db.StringProperty( default=None )
    hcp_index = db.FloatProperty( default=None )

    # cycling data
    cycling_licence = db.StringProperty( default=None )
    
    # trekking data
    trekking_licence = db.StringProperty( default=None )

    # cycling data
    running_licence = db.StringProperty( default=None )


#===================================================================================
class Golf( db.Model ):
    id = db.IntegerProperty( required=True )
    name = db.StringProperty( required=True )
    street_address = db.StringProperty()
    postal_address = db.StringProperty()
    city_address = db.StringProperty()
    phone = db.StringProperty()


#===================================================================================
class GolfCourse( db.Model ):
    golf = db.ReferenceProperty( Golf , required=True )  
    name = db.StringProperty( required=True )
    hole_number = db.IntegerProperty( required=True )
    
    pars = db.ListProperty( int )
    hcps = db.ListProperty( int )
    distance_blacks = db.ListProperty( int )
    distance_whites = db.ListProperty( int )
    distance_yellows = db.ListProperty( int )
    distance_blues = db.ListProperty( int )
    distance_reds = db.ListProperty( int )
    
    sss_male_black = db.FloatProperty( default=72.0 )
    sss_male_white = db.FloatProperty( default=72.0 )
    sss_male_yellow = db.FloatProperty( default=72.0 )
    sss_male_blue = db.FloatProperty( default=72.0 )
    sss_male_red = db.FloatProperty( default=72.0 )
    sss_female_yellow = db.FloatProperty( default=72.0 )
    sss_female_blue = db.FloatProperty( default=72.0 )
    sss_female_red = db.FloatProperty( default=72.0 )
    
    slope_male_black = db.IntegerProperty( default=113 )
    slope_male_white = db.IntegerProperty( default=113 )
    slope_male_yellow = db.IntegerProperty( default=113 )
    slope_male_blue = db.IntegerProperty( default=113 )
    slope_male_red = db.IntegerProperty( default=113 )
    slope_female_yellow = db.IntegerProperty( default=113 )
    slope_female_blue = db.IntegerProperty( default=113 )
    slope_female_red = db.IntegerProperty( default=113 )


#===================================================================================
class UserGolfEntry( db.Model ):
    user = db.ReferenceProperty( User , required=True )
    golf_course = db.ReferenceProperty( GolfCourse , required=True )
    date = db.DateProperty( required=True )
    competition = db.BooleanProperty( default=False )

    pars = db.ListProperty( int ) # repeat data from GolfCourse
    fairways = db.ListProperty( bool )
    first_club = db.ListProperty( int )
    last_club = db.ListProperty( int )
    girs = db.ListProperty( bool )
    distance_first_put = db.ListProperty( float )
    puts = db.ListProperty( int )
    scores = db.ListProperty( int )
    scrambles = db.ListProperty( bool )

    partners = db.ListProperty( str )
    comment = db.TextProperty()

    def par(self):
        sum_par = 0
        for par in self.pars:
            sum_par += par
        return sum_par
    
    def score(self):
        sum_score = 0
        for score in self.scores:
            sum_score += score
        return sum_score
    
    def relative_score(self):
        return self.score() - self.par()

    def gir(self):
        avg_gir = 0
        count = 0
        for gir in self.girs:
            count += 1
            if gir:
                avg_gir += 100
        if 0 < count:
            avg_gir /= count
        return avg_gir
    
    def avg_put(self):
        sum_put = 0.0
        count = 0
        for put in self.puts:
            sum_put += put
            count += 1
        if 0 < count:
            sum_put /= float(count)
        return round(sum_put, 2)
    
    def fairway(self):
        avg_fwy = 0
        count = 0
        for par,fwy in zip(self.pars, self.fairways):
            if 3 != par:
                count += 1
                if fwy:
                    avg_fwy += 100
        if 0 < count:
            avg_fwy /= count
        return avg_fwy