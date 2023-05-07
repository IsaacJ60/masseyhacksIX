import openai

API_KEY = open("src\\classes\\implement\\docxcard\\API_KEY.txt", "r").read()
openai.api_key = API_KEY

chatLog = []

with open("src\\assets\\text\\pptxtext.txt", "r", errors="ignore") as file:
    text = file.read()
    text = ''.join(text.splitlines())

chatLog = [{"role": "user", "content": "Summarize the following text: " + text}]
response = openai.ChatCompletion.create(
    model = "gpt-3.5-turbo",
    messages = chatLog
)

assistantResponse = response["choices"][0]["message"]["content"]

# chatLog2 = [{"role": "user", "content": "Summarize the following text in a note format, with bullet points and a title: " + text}]
# response2 = openai.ChatCompletion.create(
#     model = "gpt-3.5-turbo",
#     messages = chatLog
# )
#
# assistantResponse2 = response2["choices"][0]["message"]["content"]


with open("src\\assets\\text\\pptxsummarized.txt", "w") as file:
    file.write(assistantResponse.strip("\n").strip())

# print(assistantResponse2.strip("\n").strip())
