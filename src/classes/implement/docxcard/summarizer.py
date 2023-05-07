import openai

API_KEY = open("src\\classes\\implement\\docxcard\\API_KEY.txt", "r").read()
openai.api_key = API_KEY

with open("src\\assets\\text\\input.txt", "r", errors="ignore") as file:
    text = file.read()

chatLog = [{
    "role": "user",
    "content": "Can you summarize the following information (don't print out anything like 'this text is saying:...,' just get straight into the details): '"+text+"'"
}]

response = openai.ChatCompletion.create(
    model = "gpt-3.5-turbo",
    messages = chatLog
)

assistantResponse = response["choices"][0]["message"]["content"]

sumTXT = open("src\\assets\\text\\docxcard\\summarized.txt", "w")

sumTXT.write(assistantResponse.strip("\n").strip())

sumTXT.close()