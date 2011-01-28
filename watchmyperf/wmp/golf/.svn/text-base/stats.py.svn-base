# coding=utf8
import os

from google.appengine.ext.webapp import RequestHandler
from google.appengine.ext.webapp import template

from wmp import engine
from wmp import auth
from wmp.models import UserGolfEntry
import settings

#===================================================================================
class StatGraphic():
    #-------------------------------------------------------------------------------
    def __init__(self, name, width, height, entries):
        self._name = name
        self._width = width
        self._height = height
        self._entries = entries
        
        self._args = {"container":self._name, "data" : self.data(), "width":self._width, "height":self._height}

    #-------------------------------------------------------------------------------
    def name(self):
        return self._name

    #-------------------------------------------------------------------------------
    def width(self):
        return self._width
    
    #-------------------------------------------------------------------------------
    def height(self):
        return self._height

    #-------------------------------------------------------------------------------
    def data(self):
        return ""

    #-------------------------------------------------------------------------------
    def js(self):
        result = ""
        try:
            content_template_dir = os.path.join(settings.TEMPLATE_DIRS, "golf")
            content_template_name = os.path.join(content_template_dir, self._name + ".js")
            result = template.render(content_template_name, self._args )
        except:
            pass

        return result

#===================================================================================
class StatPerso(StatGraphic):
    #-------------------------------------------------------------------------------
    def __init__(self, entries):
        StatGraphic.__init__(self, "stat_perso", 550, 100, entries)

    #-------------------------------------------------------------------------------
    def avg_score(self):
        avg_score = 0
        avg_rscore = 0
        sum_score = 0
        sum_rscore = 0
        score_nbr = 0
        for entry in self._entries:
            sum_score += entry.score()
            sum_rscore += entry.relative_score()
            score_nbr += 1
        
        if 0 != score_nbr:
            avg_score = round(float(sum_score)/float(score_nbr), 2)
            avg_rscore = round(float(sum_rscore)/float(score_nbr), 2)
        return (avg_score, avg_rscore)

    #-------------------------------------------------------------------------------
    def avg_gir(self):
        avg_gir = 0
        sum_gir = 0
        gir_nbr = 0
        for entry in self._entries:
            sum_gir += entry.gir()
            gir_nbr += 1
        
        if 0 != gir_nbr:
            avg_gir = round(float(sum_gir)/float(gir_nbr), 2)
        return avg_gir
    
    #-------------------------------------------------------------------------------
    def avg_fairway(self):
        avg_fwy = 0
        sum_fwy = 0
        fwy_nbr = 0
        for entry in self._entries:
            sum_fwy += entry.fairway()
            fwy_nbr += 1
        
        if 0 != fwy_nbr:
            avg_fwy = round(float(sum_fwy)/float(fwy_nbr), 2)
        return avg_fwy
    
    #-------------------------------------------------------------------------------
    def avg_put(self):
        avg_put = 0
        sum_put = 0
        put_nbr = 0
        for entry in self._entries:
            sum_put += entry.avg_put()
            put_nbr += 1
        
        if 0 != put_nbr:
            avg_put = round(float(sum_put)/float(put_nbr), 2)
        return avg_put
    
    #-------------------------------------------------------------------------------
    def js(self):
        result = ""
        try:
            content_template_dir = os.path.join(settings.TEMPLATE_DIRS, "golf")
            content_template_name = os.path.join(content_template_dir, self._name + ".js")
            args = {"container":self._name}
            (args["avg_score"], args["avg_rscore"]) = self.avg_score()
            args["avg_gir"] = self.avg_gir()
            args["avg_fairway"] = self.avg_fairway()
            args["avg_put"] = self.avg_put()
            
            result = template.render(content_template_name, args )
        except:
            pass

        return result

#===================================================================================
class ScoreEvo(StatGraphic):
    #-------------------------------------------------------------------------------
    def __init__(self, entries):
        self._name = "score_evo"
        self._entries = entries
        StatGraphic.__init__(self, "score_evo", 550, 80, entries)

    #-------------------------------------------------------------------------------
    def data(self):
        score_nbr = 0
        avg_score = {-4:0, -3:0, -2:0, -1:0, 0:0, 1:0, 2:0, 3:0, 4:0}
        score_name = {-4:"condor", -3:"albatros", -2:"eagle", -1:"birdie", 0:"par", \
                      1:"bogey", 2:"double bogey", 3:"triple bogey", 4:"quadruple bogey"}
        for entry in self._entries:
            for score,par in zip(entry.scores, entry.pars):
                score_nbr += 1
                relative_score = score - par
                if relative_score in avg_score:
                    avg_score[relative_score] += 1
        
        data = ""
        if 0 != score_nbr:
            for score,sum in avg_score.iteritems():
                avg = round(100.0*sum/score_nbr, 2)
                name = score_name[score]
                if 0 != avg:
                    data += """['%s', %s],""" % (name, avg)
        return data

    #-------------------------------------------------------------------------------
    def js(self):
        result = ""
        try:
            content_template_dir = os.path.join(settings.TEMPLATE_DIRS, "golf")
            content_template_name = os.path.join(content_template_dir, self._name + ".js")
            args = {"container":self._name}
            args["avg_scores"] = []
            args["avg_girs"] = []
            args["avg_fairways"] = []
            args["avg_puts"] = []
            for entry in self._entries:
                args["avg_scores"] += entry.relative_score()
                args["avg_girs"] += entry.gir()
                args["avg_fairways"] += entry.fairway()
                args["avg_puts"] += entry.avg_put()

            result = template.render(content_template_name, args )
        except:
            pass

        return result


#===================================================================================
class AvgScore(StatGraphic):
    #-------------------------------------------------------------------------------
    def __init__(self, entries):
        StatGraphic.__init__(self, "avg_score", 550, 400, entries)

    #-------------------------------------------------------------------------------
    def data(self):
        score_nbr = 0
        avg_score = {-4:0, -3:0, -2:0, -1:0, 0:0, 1:0, 2:0, 3:0, 4:0}
        score_name = {-4:"condor", -3:"albatros", -2:"eagle", -1:"birdie", 0:"par", \
                      1:"bogey", 2:"double bogey", 3:"triple bogey", 4:"quadruple bogey"}
        for entry in self._entries:
            for score,par in zip(entry.scores, entry.pars):
                score_nbr += 1
                relative_score = score - par
                if relative_score in avg_score:
                    avg_score[relative_score] += 1
        
        data = ""
        if 0 != score_nbr:
            for score,sum in avg_score.iteritems():
                avg = round(100.0*sum/score_nbr, 2)
                name = score_name[score]
                if 0 != avg:
                    data += """['%s', %s],""" % (name, avg)
        return data


#===================================================================================
class Handler(RequestHandler):
    #-------------------------------------------------------------------------------
    def get(self):
        args = {}
        user = auth.identify_user_by_cookie(self)
        if user:
            entries = UserGolfEntry.gql("WHERE user = :1", user)
            avg_score = AvgScore(entries)
            evo = StatGraphic("score_evo", 550, 400, entries)
            stat_perso = StatPerso(entries)
            args["stats"] = [stat_perso, avg_score, evo]
        
        page = engine.page(self, args)
        self.response.out.write(page)
