# file: FlowSolver.py
# author: Seungmin Lee
# date: December 09, 2014

# This is a program that solves the Flow game.
# See e.g., http://moh97.us/flow/
#


###################

# INSTRUCTION :
# 1. Pick puzzle from drop down menu.
# 2. Create Puzzle
# 3. Solve
# 4. Eat popcorn and watch it solve.
# 5. Change 'solve speed' from slider if you want to see it in slow-mo.


from Tkinter import *
from time import sleep
import pprint

# Debugging
#
DEBUG = False

# The Board
#
# This version of flow is played on an 6 x 6 board.
#
N = 6

# Shapes
#
# There are 6 playable shapes and 1 unplayble end shape.
#
nShapes = 7    #  == 6 + 1

# Give the shapes numbers and names.
#                         
shapes = range(nShapes)
[Horizontal, Vertical, Q1, Q2, Q3, Q4, Circle] = shapes
shapeNames = ['Horizontal', 'Vertical', 'Q1', 'Q2', 'Q3', 'Q4', 'Circle']

# NB:
#
# Q1 = |    Q2 =   |  Q3 = --+  Q4 = +--
#      +--       --+         |       |
#
def shapeOf(piece): return piece % nShapes
def shapeName(piece): return shapeNames[shapeOf(piece)]

def isEndPiece(piece): return shapeOf(piece) == Circle

# Colors
#
# For curr, we have 9 colors: blue, cyan, green, magenta, orange, 
# pink, red, white and yellow.
#
nColors = 9
colors = range(nColors)
colorNames = ['blue', 'cyan', 'green', 'magenta', 'orange', 'pink', 'red', 'white', 'yellow']

def colorOf(piece): return piece / nShapes
def colorName(piece): return colorNames[colorOf(piece)]

def sameColor(piece1, piece2): return colorOf(piece1) == colorOf(piece2)

# Pieces
#
pieces = range(nShapes * nColors)

# Circles are end pieces. They are specified by the user so they
# are unplayable by the program.
#

betterPlayablePieces = []
playingColors = [] # This is to use only the colors necessary to solve. (for efficiency)

# Two special pieces for empty cells and an outer wall.
#
[Empty, Wall] = [len(pieces), len(pieces) + 1]

# pieceName : piece -> string
#
# The call pieceName(piece) returns the name of the piece.
#
def pieceName(piece):
    if piece == Wall:
        return 'Wall'
    elif piece == Empty:
        return 'Empty'
    else:
        return colorName(piece) + shapeName(piece)

# isEmpty : piece -> boolean
#
def isEmpty(piece): return piece == Empty

# placePiece : piece * board * row * col -> void
#
# The call placePiece(piece, board, row, col) will place piece at
# row, col in the board. It also takes care of updating the user
# interface. You don't have to deal with the user interface.
#
def placePiece(piece, board, row, col):
    global buttonFinder, imageDictionary
    if shapeOf(piece) in [Circle]:
        whatColorsAreThere(piece)
    board[row][col] = piece
    image = imageDictionary[piece]
    button = buttonFinder[(row, col)]
    button.config(image=image)
    button.image = image


#whatColorsAreThere(piece) makes a list of colors used in the puzzle.
def whatColorsAreThere(piece):
    global playingColors
    if colorOf(piece) in playingColors:
       return
    else:
        playingColors.extend([colorOf(piece)])
        
# show : board -> void
#
# The call show(board) will show a visualization of the board in
# the user interface. The show function only updates the user
# iterface sometimes.  It's too slow to update it on every change.
# You do not have to deal with the user interface.
#
def show(board):
    global showCount
    showSkips = 1000
    showCount += 1
    if showCount % showSkips == 0:
        speed = scale.get()
        userInterface.update()
        userInterface.update_idletasks()
        sleep(speed/1000.0) # this will control the speed according to the Scale UI.
  
# Predicates for piece directions. E.g., goesUp : piece -> boolean
#
def goesUp(piece):
    return isEmpty(piece) or (piece != Wall and shapeOf(piece) in [Vertical, Q1, Q2, Circle])

def goesDown(piece):
    return isEmpty(piece) or (piece != Wall and shapeOf(piece) in [Vertical, Q3, Q4, Circle])

def goesLeft(piece):
    return isEmpty(piece) or (piece != Wall and shapeOf(piece) in [Horizontal, Q2, Q3, Circle])

def goesRight(piece):
    return isEmpty(piece) or (piece != Wall and shapeOf(piece) in [Horizontal, Q1, Q4, Circle])


# See if the current piece is OK with respect to what is in the
# cell above.
#
def isAllowedAbove(above, curr):
    if isEmpty(above):
        return True # empty cells always allowed above
    if not goesUp(curr) and not goesDown(above):
        return True # tiles do not join each other; allowed
    if not goesUp(curr) and isEndPiece(above):
        return True # endpoints go every direction, but it's not required that you enter them
    if goesUp(curr) and goesDown(above) and sameColor(curr, above):
        return True # "normal" case of connecting tiles
    if DEBUG: print 'isnt allowed Above'
    return False # everything else

def isAllowedBelow(below, curr):
    if isEmpty(below):
        return True
    if not goesDown(curr) and not goesUp(below):
        return True
    if not goesDown(curr) and isEndPiece(below):
        return True
    if goesDown(curr) and goesUp(below) and sameColor(curr, below):
        return True
    if DEBUG: print 'isnt allowed Below'
    return False

def isAllowedLeft(left, curr):
    if isEmpty(left):
        return True
    if not goesLeft(curr) and not goesRight(left):
        return True
    if not goesLeft(curr) and isEndPiece(left):
        return True
    if goesLeft(curr) and goesRight(left) and sameColor(curr, left):
        return True
    if DEBUG: print 'isnt allowed Left'
    return False

def isAllowedRight(right, curr):
    if isEmpty(right):
    	return True
    if not goesRight(curr) and not goesLeft(right):
        return True
    if not goesRight(curr) and isEndPiece(right):
    	return True
    if goesRight(curr) and goesLeft(right) and sameColor(curr, right):
    	return True
    if DEBUG: print  'isnt allowed Right'
    return False

#           checkCell : board * row * col -> boolean
#
#           checks to see if the piece at board[row][col]
#           is OK with respect to its neighbors above, below,
#           left and right.  In order for the piece to be OK,
#           it has to be compatible with -all- of it's neighbors
#           as would be tested by the isAllowedXXX functions above.
#
def checkCell(board, row, col):
    curr = board[row][col]
    left = board[row][col - 1]
    right = board[row][col + 1]
    above = board[row - 1][col]
    below = board[row + 1][col]
    if isAllowedRight(right, curr) and isAllowedLeft(left, curr) and isAllowedAbove(above, curr) and isAllowedBelow(below, curr):
    	return True
    return False

# Check to see if a given end piece is happy with its neighbors.
#
def endPieceEntryOK(board, row, col):
        entries = 0;
        left  = board[row][col - 1]
        right = board[row][col + 1]
        above = board[row - 1][col]
        below = board[row + 1][col]
        empties = 0;
        if isEmpty(left):
            empties += 1
        if isEmpty(right):
            empties += 1
        if isEmpty(above):
            empties += 1
        if isEmpty(below):
            empties += 1
            
        if not isEmpty(left) and (not isEndPiece(left)) and goesRight(left):
            entries += 1
        if not isEmpty(right) and (not isEndPiece(right)) and goesLeft(right):
            entries += 1
        if not isEmpty(above) and (not isEndPiece(above)) and goesDown(above):
            entries += 1
        if not isEmpty(below) and (not isEndPiece(below)) and goesUp(below):
            entries += 1
                                  
        if entries > 1 or (entries == 0 and empties == 0):
            return False
        return True


#              endPieceEntriesOK : board -> boolean
#
#              This function should search through the board
#              and for any end piece found, it should confirm
#              that it is OK with respect to its neighbors. 
#              If so, return True. If not, return False.
#
def endPieceEntriesOK(board):
    for row in range(1,N+1):
        for col in range(1,N+1):
            if isEndPiece(board[row][col]) and not endPieceEntryOK(board, row, col):
                return False
    return True

def tryAllPieces(board, row, col):
    #betterPlayablePieces checks for color compatibility for efficiency
    #
    betterPlayablePieces = [] 
    for i in playingColors:
        betterPlayablePieces.extend(range(7*i, 7*i+6))

    for piece in betterPlayablePieces:
        placePiece(piece, board, row, col)
        show(board)
        if checkCell(board, row, col) and endPieceEntriesOK(board):
            if solve(board):
                return True                
    placePiece(Empty, board, row, col)
    show(board)
    return False

#              tryAllPieces : board * row * col -> boolean
#
#              This function should try every playable piece in 
#              board[row][col]. (use the placePiece function, then 
#              show(board)).
#              
#              Once a piece is placed on the board, it should be
#              checked to ensure that it's compatible with it's
#              neighbors.  If it is, see if all of the end piece
#              entries are OK. If they are, then recursively try to
#              solve the board. If it succeeds, then you're done:
#              return True.
#
#              If on the other hand, the piece isn't compatible with
#              its neighbors, or any of the end pieces are not OK or
#              the remaining board was unsolvable, then move on to try
#              the next playable piece.
#
#              If none of the playable pieces lead to a solution, then
#              tryAllPieces failed. Place the Empty piece in the board
#              at row, col, show the board, and return False.

                                  
# solve the board by playing a piece in first empty cell. Use
# for-loops together with recursion to implement backtracking.
#
def solve(board):
    for row in range(1,N+1):
        for col in range(1,N+1):
            if board[row][col] == Empty:
                if tryAllPieces(board,row,col) and endPieceEntriesOK(board):
                    return True
                else:
                    placePiece(Empty,board,row,col)
                    show(board)
                    return False
    print "Solved in " + str(showCount) + " Tries."
    return True



# initPiece(row, col, N) returns the appropriate piece for position
# row, col for the intial board. These are either empty or walls.
#
def initPiece(row, col, N):
    if row == 0 or row == N - 1 or col == 0 or col == N - 1:
        return Wall
    else:
        return Empty

# getEndPiece(n) returns the same end piece for two consecutive
# values n and n+1. Used in initialization.
#
def getEndPiece(n): return pieces[((n / 2) + 1) * nShapes - 1]


# The show starts here!
#
def start():
    global userInterface
    Ns = range(N + 2)  # Two extra rows and columns for walls.
    board = [[ initPiece(row, col, N + 2) for col in Ns] for row in Ns]
    makeUserInterface(board)    
    userInterface.mainloop()


# makeImageDictionary : pieces -> (piece, image) dictionary
#
# This function makes a dictionary mapping pieces to images of
# those pieces.
#
def makeImageDictionary(pieces):
    dictionary = {}
    for piece in pieces:
        fileName = pieceName(piece) + '.gif'       
        dictionary[piece] = PhotoImage(file="PieceImages/" + fileName)
    dictionary[Empty] = PhotoImage(file="PieceImages/Empty.gif")
    dictionary[Wall] = PhotoImage(file="PieceImages/Wall.gif")

    return dictionary

clickCount = 0
buttonFinder = {}

def makeUserInterface(board):

    global userInterface, buttonFinder, imageDictionary

    
    def buttonClick(event):
        global clickCount
        endPiece = getEndPiece(clickCount)
        row = event.widget.row
        col = event.widget.col
        placePiece(endPiece, board, row, col)
        clickCount = clickCount + 1

    userInterface = Tk()
    userInterface.title("CS101 Flow Solver")
    var = DoubleVar()
    imageDictionary = makeImageDictionary(pieces)

    for row in range(N + 2):
        for col in range(N + 2):
            piece = board[row][col]
            image = imageDictionary[piece]
            if isEmpty(piece):
                button = Button(userInterface, image=image)
                button.row = row
                button.col = col

                # Save the reference to this button widget so we can find
                # it later having only board coordinates row and col. No 
                # doubt there is a better way to do this ...
                #
                buttonFinder[(row, col)] = button
                
                button.grid(row=row - 1, column=col - 1)
                button.bind("<Button-1>", buttonClick)
            else:              
                button = Label(userInterface, image=image)                

            button.image = image
    global scale
    scale = Scale(userInterface ,orient=HORIZONTAL, variable = IntVar(), label="Solve Speed", from_=0, to_=100)
    scale.grid(row = N + 2, column = 0, columnspan = 2)
    
    solveButton = Button(userInterface, text="Solve", command=lambda : solve(board))
    solveButton.grid(row=N + 2, column=2, columnspan=2)

    testButton = Button(userInterface, text="Create Puzzle", command=lambda : eval(var.get())(board))
    testButton.grid(row=N + 3, column=3, columnspan=3)
    
    var = StringVar(userInterface)
    var.set("Pick Puzzle")

    option = OptionMenu(userInterface, var, "puzzle1", "puzzle2", "puzzle3", "puzzle4")
    option.grid(row=N + 2, column = 3, columnspan =3)
    
# Make Example Puzzles
#
def test1(board):
    placePiece(Q1, board, 3, 3)
    placePiece(Q1, board, 4, 4)
    if DEBUG: pprint.pprint(board)
    show(board)

def puzzle1(board):
    p0 = getEndPiece(0)
    p1 = getEndPiece(2)
    p2 = getEndPiece(4)
    p3 = getEndPiece(6)
    placePiece(p0, board, 2, 2)
    placePiece(p0, board, 5, 3)
    placePiece(p1, board, 3, 4)
    placePiece(p1, board, 6, 4)
    placePiece(p2, board, 6, 3)
    placePiece(p2, board, 5, 4)
    placePiece(p3, board, 2, 5)
    placePiece(p3, board, 5, 5)
    show(board)

def puzzle2(board):
    p0 = getEndPiece(2)
    p1 = getEndPiece(6)
    p2 = getEndPiece(0)
    p3 = getEndPiece(10)
    p4 = getEndPiece(12)
    placePiece(p0, board, 1, 3)
    placePiece(p0, board, 3, 1)
    placePiece(p1, board, 2, 2)
    placePiece(p1, board, 5, 5)
    placePiece(p2, board, 5, 2)
    placePiece(p2, board, 6, 1)
    placePiece(p3, board, 2, 3)
    placePiece(p3, board, 3, 6)
    placePiece(p4, board, 5, 1)
    placePiece(p4, board, 2, 5)
    show(board)


def puzzle3(board):
    p0 = getEndPiece(4)
    p1 = getEndPiece(6)
    p2 = getEndPiece(8)
    p3 = getEndPiece(12) 
    p4 = getEndPiece(14)
    placePiece(p0, board, 2, 1)
    placePiece(p0, board, 4, 6)
    placePiece(p1, board, 3, 2)
    placePiece(p1, board, 5, 2)
    placePiece(p2, board, 2, 2)
    placePiece(p2, board, 5, 6)
    placePiece(p3, board, 4, 2)
    placePiece(p3, board, 6, 1)
    placePiece(p4, board, 2, 4)
    placePiece(p4, board, 5, 5)
    show(board)

def puzzle4(board):
    p0 = getEndPiece(4)
    p1 = getEndPiece(6)
    p2 = getEndPiece(8)
    p3 = getEndPiece(12) 
    p4 = getEndPiece(14)
    p5 = getEndPiece(0)
    placePiece(p0, board, 1, 1)
    placePiece(p0, board, 1, 6)
    placePiece(p1, board, 1, 2)
    placePiece(p1, board, 3, 2)
    placePiece(p2, board, 1, 3)
    placePiece(p2, board, 1, 5)
    placePiece(p3, board, 2, 4)
    placePiece(p3, board, 3, 3)
    placePiece(p4, board, 4, 2)
    placePiece(p4, board, 2, 5)
    placePiece(p5, board, 5, 2)
    placePiece(p5, board, 4, 5)
    show(board)
    

showCount = 0   
start()
