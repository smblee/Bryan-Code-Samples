# file: smText.py
# author: Seungmin Bryan Lee
# date: November 25, 2014

# This file contains scaffolding for a python program that will generate 
# random text that is statistically constrained by a Markov analysis of 
# some input text. The idea was first suggested by Prof. Claude Shannon in
# in 1948.  
#
import random
import pprint

from Tkinter import *
import tkFileDialog
import ScrolledText

DEBUG = False

class Markov():

    def __init__(self, root):
        frame1 = Frame(root)
        frame2 = Frame(root)
        frame3 = Frame(root)

        frame1.grid(row=0, column=0)
        frame3.grid(row=0, column=1)
        frame2.grid(row=0, column=2)

        self.inputText = ScrolledText.ScrolledText(frame1, bg='lightgray')
        loadButton = Button(frame1, text="load", command=self.load_file)
        loadButton.grid(row = 0, column = 0)
        self.inputText.grid(row=1, column=0)

        self.outputText = ScrolledText.ScrolledText(frame2, bg='orange')
        
        saveButton = Button(frame2, text="save", command=self.save_file)
        saveButton.grid(row = 0, column = 0)
        self.outputText.grid(row=1, column=0)

        self.degree = StringVar()
        degreeEntry = Entry(frame3, textvariable=self.degree)
        degreeEntry.insert(1, '4')

        generate = Button(frame3, text=">>>", command=self.generate)
        degreeEntry.grid(row=0, column=0)
        generate.grid(row=1, column=0)

    def load_file(self):
        f_name = tkFileDialog.askopenfilename(initialdir="./", title="Pick a text file")
        if(f_name != ''):

            # Open the selected file
            #
            file_in = open(f_name, 'r')

            # Read the file and load the input field.
            #
            lines = file_in.readlines()
            self.inputText.insert(1.0, lines)
            file_in.close()
    
    def save_file(self):
        file_out = tkFileDialog.asksaveasfile(initialdir="./", mode='w',defaultextension='.txt')

        # Write the output text field to the file and close the file
        #
        file_out.write(self.outputText.get(0.0, END))
        file_out.close()

    def getDegree(self):
        return int(self.degree.get())

    def getInputText(self):
        return self.inputText.get(0.0, END)

    def putOutputText(self, text):
        self.outputText.insert(1.0, text)
    
    def generate(self):
        degree = self.getDegree()
        loadedText = str(self.getInputText())
        model = Model(degree, loadedText)
        phrases = model.getKeys()
        Dict = model.getAll()
        phrase = "" # empty phrase to begin
        characters = Dict[phrase]
        randomNumber = random.randint(0, len(characters) - 1)
        char = characters[randomNumber]

#        print Dict
        def loop(acc, currentChar, phrase, n):
            if n > 950: #too much recursion will cause error. So put limit.
                self.putOutputText(acc)
            if currentChar == None:
                self.putOutputText(acc)
            else:
       #         print phrase + currentChar
                newPhrase = getPhrase(acc, phrase, currentChar)
                yay = newPhrase if len(newPhrase) < degree else newPhrase[1:]
                #print "yay: " + str(yay)
                #print "newPhrase: " +  str(newPhrase)
                characters = Dict[newPhrase]
                randomNumber = random.randint(0, len(characters) - 1)
                char = characters[randomNumber]
                #print acc + currentChar
                return loop(acc + currentChar, char, yay, n+1)
        return loop("", char, '', 0)


#Class Model creates a dictionary of possible combinations.
#It varies with degree. The functions in it are used for Markov class
#to generate output.

class Model():
    def __init__(self, degree, txt):
        d = {}
        self.degree = degree
        self.txt = txt
        phrase = '' #sets the phrase that comes before first letter as empty string.

        for char in txt:        
            if phrase in d: #if already in dictionary d.
                d[phrase].append(char)
            else: #if not in dictionary, make new key.
                d[phrase] = [char]
            phrase = getPhrase(degree + 1, phrase, char) #get new Phrase

        #print phrase
        d[phrase] = [None] #Attaches None to last phrase key.
        self.d = d
        #print d

    def getKeys(self):
        return self.d.keys()

    def getAll(self):
        return self.d

def getPhrase(degree, previousPhrase, currentChar):
    if len(previousPhrase + currentChar) < degree:
        return previousPhrase + currentChar
    else:
        #newPhrase = loadedText[n - degree:n]
        return (previousPhrase + currentChar)[1:]

def main():
    root = Tk()    
    root.title("Shannon/Markov Model of Text")
    mark = Markov(root)

    root.mainloop()

main()
