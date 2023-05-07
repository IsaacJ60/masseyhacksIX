import openai

openai.api_key = "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"

with open("src\\assets\\text\\input.txt", "r") as file:
    text = file.read()
    
chatLog = [{"role": "user", "content": "Can you summarize the following information (don't print out anything like 'this text is saying:...,' just get straight into the details): '"+text+"'"}]

response = openai.ChatCompletion.create(
    model = "gpt-3.5-turbo",
    messages = chatLog
)

assistantResponse = response["choices"][0]["message"]["content"]

sumTXT = open("src\\assets\\text\\docxcard\\summarized.txt", "w")
res = assistantResponse.strip("\n").strip()

sumTXT.write(assistantResponse.strip("\n").strip())

sumTXT.close()
