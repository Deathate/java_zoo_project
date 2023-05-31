import socket
import json
import requests

def get_ngrok_url():
    url = "http://localhost:4040/api/tunnels/"
    res = requests.get(url)
    res_unicode = res.content.decode("utf-8")
    res_json = json.loads(res_unicode)
    for i in res_json["tunnels"]:
        if i['name'] == 'command_line':
            return i['public_url']
ngrok_path = get_ngrok_url()

def sendRequest(word: str, dct,debug=False):
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(("localhost", 5080))
    # client_socket.connect(("0.tcp.jp.ngrok.io", 19664))
    word = word+ "/"+json.dumps(dct)
    client_socket.send(bytes(word, "utf8"))
    length = int(client_socket.recv(8).decode())
    data = client_socket.recv(length).decode()
    data = json.loads(data)
    client_socket.close()

    if debug:
        print(json.dumps(data,indent=2,ensure_ascii=False))
        with open("debug.json","w",encoding="utf8") as f:
            f.write(json.dumps(data,indent=2,ensure_ascii=False))
    return data

def loadImage(src):
    file_name = src.split("\\")[-1]
    return "https://raw.githubusercontent.com/Deathate/java_zoo_project/main/static/"+ file_name

# sendRequest("get/animals",None,True)
# sendRequest("get/animals",{"name":"羊駝"},True)
# print(loadImage(d["body"]["path"]))
# sendRequest("get/animals",{"start":0,"end":12},True)
# sendRequest("post/tickets",{"id":"admin","tname":"幼兒票","date":"2000-06-01"},True)
# sendRequest("get/tickets", {"kind":"user","id": "admin"},True)
# sendRequest("get/tickets_length", {"id": "admin"},True)
# sendRequest("get/tickets",{"kind":"entrance"},True)
# s=sendRequest("get/tickets",{"kind":"experience"},True)
# sendRequest("get/linemap",{"start":"入口","end":"羊之丘"},False)
# print(loadImage(d["body"]["path"]))
# sendRequest("get/tickets", {"kind":"user","id": "admin"},True)
# sendRequest("delete/tickets",{"id":"admin"},True)
# sendRequest("get/tickets", {"kind":"user","id": "admin"},True)
# sendRequest("get/zones",{},True)
