# file: extraTess.py
# author: Seungmin Lee
# date: November 21, 2014
#
# must have stddraw library installed to run this.
from stddraw import *
import math
import random

picture = Picture()



############# Colors ##################

colorScheme = "blue" # blue or dark

# Dark Scheme
dr1, dg1, db1 = (48,46,38) # BRIGHTEST
dr2, dg2, db2 = (29,27,19) # DARKESET
dr3, dg3, db3 = (33,32,26) # MIDDLE


# Blue Scheme
r1, g1, b1 = (136,170,255) # BRIGHTEST
r2, g2, b2 = (17,0,187) # DARKESET
r3, g3, b3 = (34,119,170) # MIDDLE



color1 = picture.makeColor(r1,g1,b1) if colorScheme == "blue" else picture.makeColor(dr1,dg1,db1)
color2 = picture.makeColor(r2,g2,b2) if colorScheme == "blue" else picture.makeColor(dr2,dg2,db2)
color3 = picture.makeColor(r3,g3,b3) if colorScheme == "blue" else picture.makeColor(dr3,dg3,db3)
#######################################

lst1, lst2, lst3, lst4 = ([],[],[],[]) #Empty lists for keeping track of full cells.  
side = 0.1 #Value of sides. 
radius = 0.05 
delay = 20 # ms

DEBUG = False


def makeDupside(picture,x,y,color='default'):
    x11, y11 = (x, y)
    x12, y12 = (x + side * 0.5, y + side * 0.5)
    x13, y13 = (x + side * 0.5, y + side * 1.5)
    x14, y14 = (x, y + side)
    xcoor1 = [x11, x12, x13, x14]
    ycoor1 = [y11, y12, y13, y14]
    picture.filledPolygon(xcoor1, [y-radius for y in ycoor1], color2)
    picture.polygon(xcoor1, [y-radius for y in ycoor1])

def makeDupside2(picture,x,y,color='default'):
    x11, y11 = (x, y)
    x12, y12 = (x - side * 0.5, y + side * 0.5)
    x13, y13 = (x - side * 0.5, y + side * 1.5)
    x14, y14 = (x, y + side)
    xcoor1 = [x11, x12, x13, x14]
    ycoor1 = [y11, y12, y13, y14]
    picture.filledPolygon(xcoor1, [y-radius for y in ycoor1], color2)
    picture.polygon(xcoor1, [y-radius for y in ycoor1])
    
def makeBotside(picture,x,y,color='default'):
    x1, y1 = (x, y)
    x2, y2 = (x+side, y)
    x3, y3 = (x+side * 1.5, y+side * 0.5)
    x4, y4 = (x+side * 0.5, y+side * 0.5)
    xcoor = [x1, x2, x3, x4]
    ycoor = [y1, y2, y3, y4]
    picture.filledPolygon([x-radius for x in xcoor], ycoor, color1)
    picture.polygon([x-radius for x in xcoor], ycoor)

def makeBotside2(picture,x,y,color='default'):
    x1, y1 = (x, y)
    x2, y2 = (x-side, y)
    x3, y3 = (x-side * 1.5, y+side * 0.5)
    x4, y4 = (x-side * 0.5, y+side * 0.5)
    xcoor = [x1, x2, x3, x4]
    ycoor = [y1, y2, y3, y4]
    picture.filledPolygon([x-radius for x in xcoor], ycoor, color1)
    picture.polygon([x-radius for x in xcoor], ycoor)


def tesselate(picture, x, y, cube, rotate = 0):
    global lst1, lst2, lst3, lst4
    #base square
    if cube == 1:
        picture.filledSquare(x, y, radius, color3)
    elif cube == 2:
        picture.filledSquare(x, y, radius, color3)
    if -radius < x < 1 + radius and -radius < y < 1 + radius :
        if rotate == 0:
            if DEBUG == True:
                print "rotate = " + str(rotate) + ": " + str(x) + "," + str(y)
                print lst1            
            makeDupside(picture,x+radius,y)
            picture.wait(delay)
            makeBotside(picture,x,y+radius)
            if [x,y] not in lst1: #go right
                lst1.append([x,y])
                return tesselate(picture, round(x + 3 * radius,3), round(y + radius,3), 1)
            else: # go up
                lst1.append([x,y])
                return tesselate(picture, round(x + radius,3), round(y + radius * 3,3), 1)
        elif rotate == 1:
            if DEBUG == True:
                print "rotate = " + str(rotate) + ": " + str(x) + "," + str(y)
                print lst2
            makeDupside2(picture,x-radius,y, "color")
            picture.wait(delay)
            makeBotside2(picture,x + side,y + radius, "color")
            if [x,y] not in lst2: #go to left
                if DEBUG == True: print "going left"
                lst2.append([x,y])
                tesselate(picture, round(x - 3 * radius,3), round(y + radius,3), 2, rotate)
            else: # go to up
                if DEBUG == True: print "going up"
                lst2.append([x,y])
                tesselate(picture, round(x - radius,3), round(y+ radius * 3,3), 2, rotate)       

        elif rotate ==2:
            if DEBUG == True: 
                print "rotate = " + str(rotate) + ": " + str(x) + "," + str(y)
                print lst3
            makeDupside(picture,x-side,y-radius)
            picture.wait(delay)
            makeBotside(picture,x- radius,y-side)
            if [x,y] not in lst3: #go to left
                if DEBUG == True: print "going left2"
                lst3.append([x,y])
                tesselate(picture, round(x - 3 * radius, 3), round(y - radius,3), 2, rotate)
            else: # go to down
                if DEBUG == True: print "going down"
                lst3.append([x,y])
                tesselate(picture, round(x - radius,3), round(y- radius * 3,3), 2, rotate)

        elif rotate == 3:
            if DEBUG == True: 
                print "rotate = " + str(rotate) + ": " + str(x) + "," + str(y)
                print lst4
            makeDupside2(picture,x+side,y-radius, "color")
            picture.wait(delay)
            makeBotside2(picture,x + radius * 3,y - side, "color")
            if [x,y] not in lst4: #go to left
                if DEBUG == True: print "going right"
                lst4.append([x,y])
                tesselate(picture, round(x + 3 * radius,3), round(y - radius,3), 2, rotate)
            else: # go to down
                if DEBUG == True: print "going down"
                lst4.append([x,y])
                tesselate(picture, round(x + radius,3), round(y - radius * 3,3), 2, rotate)       

    else:
        if round(y,3) > 1 or round(y,3) < 0:
            if DEBUG == True: print [x,y]
            return tesselate(picture, 0.5,0.5, 2, rotate + 1)
        else:
            if DEBUG == True: print [x,y]
            return tesselate(picture, 0.5,0.5, 2, rotate)


def start():
    def responder(event):
#        picture.filledSquare(0.5, 0.5, 1, picture.makeColor(101,173,232)) # background
        tesselate(picture, 0.5,0.5, 1) #tesselation
    picture.setW(800)
    picture.setH(800)
    picture.bind("<Button-1>", responder)
    picture.start()

start()
