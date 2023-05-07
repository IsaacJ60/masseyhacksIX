import openai

openai.api_key = "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"

with open("src\\assets\\text\\docxcard\\summarized.txt", "r") as file:
    text = file.read()

chatLog = [{
    "role": "user",
    "content":'Can you generate flashcards formatted to be used in Anki with the cloze model from the following information (print them in a numbered list, i.e., 1. 2. 3.): "'+text+'". Every card needs to be 1 sentence long and have at keast 1 cloze ({{c1::}}, {{c2::}}, {{c3::}}, etc.'
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