import genanki
import uuid
import csv

deckID = uuid.uuid4().int & (1<<31)-1;

with open("src\\assets\\text\\docxcard\\deckfilename.txt", "r") as file:
    deckTitle = file.read()

deck = genanki.Deck(deckID, deckTitle)

with open("src\\assets\\text\\docxcard\\cards.txt", "r") as file:
    text = file.read()

sentences = text.split("\n\n")
fronts = [0]*len(sentences)
backs = [0]*len(sentences)

for i in range(len(sentences)):
    fronts[i], backs[i] = sentences[i].split("\n")

for i in range(len(sentences)):
    note = genanki.Note(
        model = genanki.BASIC_MODEL,
        fields = [fronts[i][7:], backs[i][6:]]
        )
    deck.add_note(note)

genanki.Package(deck).write_to_file("src/assets/temp/temp.apkg")
