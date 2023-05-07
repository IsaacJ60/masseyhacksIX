import openai

openai.api_key = "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"

with open("src\\assets\\text\\docxcard\\summarized.txt", "r") as file:
    text = file.read()

chatLog = [{
    "role": "user",
    "content":'Can you generate flashcards formatted to be used in Anki with the basic model (print them in the format "Front:\n Back:") from the following information: "'+text+'". Every card needs to be 1 sentence long.'
    }]

response = openai.ChatCompletion.create(
    model = "gpt-3.5-turbo",
    messages = chatLog
)

assistantResponse = response["choices"][0]["message"]["content"]

cardsTXT = open("src\\assets\\text\\docxcard\\cards.txt", "w")
res = assistantResponse.strip("\n").strip()

cardsTXT.write(assistantResponse.strip("\n").strip())

cardsTXT.close()
