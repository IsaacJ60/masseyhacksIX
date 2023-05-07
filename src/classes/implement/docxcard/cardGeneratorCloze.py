import genanki
import uuid

deckID = uuid.uuid4().int & (1<<31)-1;

deck = genanki.Deck(deckID, "Change Me")

with open("src\\assets\\text\\docxcard\\cards.txt", "r") as file:
    for line in file:
        note = genanki.Note(
            model = genanki.CLOZE_MODEL,
            fields = [line.strip()]
            )
        deck.add_note(note)

genanki.Package(deck).write_to_file("src/assets/temp/temp.apkg")
