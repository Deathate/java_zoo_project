from __future__ import unicode_literals
import os
from flask import Flask, request, abort
from linebot import LineBotApi, WebhookHandler
from linebot.exceptions import InvalidSignatureError
from linebot.models import *
import json
import configparser
import random
import client
from emoji import *

ngrok_path = client.ngrok_path
if __name__ == "__main__":
    app = Flask(__name__)

    # LINE 聊天機器人的基本資料
    config = configparser.ConfigParser()
    config.read('config.ini')

    line_bot_api = LineBotApi(config.get('line-bot', 'channel_access_token'))
    handler = WebhookHandler(config.get('line-bot', 'channel_secret'))

    # 接收 LINE 的資訊
    @app.route("/callback", methods=['POST'])
    def callback():
        signature = request.headers['X-Line-Signature']

        body = request.get_data(as_text=True)
        app.logger.info("Request body: " + body)

        try:
            print(body, signature)
            handler.handle(body, signature)

        except InvalidSignatureError:
            abort(400)

        return 'OK'

    @handler.add(MessageEvent, message=TextMessage)
    def handle_message(event):
        mtext = event.message.text
        if mtext == 'zoo門票' or mtext == '重新選擇':
            imgwidth = 1040
            imgheight = 1040
            ticket = client.sendRequest("get/tickets",{"kind":"entrance"},True) 
            message =[
                TextSendMessage(text='$$'+ticket['body'][0]['content']+'~'+'\n票價為：¥'+str(ticket['body'][0]['price']),emojis=emoji_one),
                TextSendMessage(text='$$'+ticket['body'][1]['content']+'~'+'\n票價為：¥'+str(ticket['body'][1]['price']),emojis=emoji_two),
                TextSendMessage(text='$'+ticket['body'][2]['content']+'~'+'\n票價為：¥'+str(ticket['body'][2]['price']),emojis=emoji_three),
                TextSendMessage(text='$$'+ticket['body'][3]['content']+'~'+'\n票價為：¥'+str(ticket['body'][3]['price']),emojis=emoji_four),
                ImagemapSendMessage(
                    base_url =  ngrok_path +'/static/ticket.jpg',
                    alt_text='組圖訊息',
                    base_size=BaseSize(imgwidth, imgheight),
                    actions=[
                        MessageImagemapAction(
                            text = '選擇' + ticket['body'][0]['name'],
                            area = ImagemapArea( #設定圖片範圍左上方
                                x = 0, 
                                y = 0,  
                                width = imgwidth *0.5, 
                                height = imgheight *0.5
                            )
                        ),
                        MessageImagemapAction(
                            text = '選擇' + ticket['body'][1]['name'],
                            area = ImagemapArea( #設定圖片範圍右上方
                                x = imgwidth *0.5, 
                                y = 0,  
                                width = imgwidth *0.5, 
                                height = imgheight *0.5
                            )
                        ),
                        MessageImagemapAction(
                            text = '選擇' + ticket['body'][2]['name'],
                            area = ImagemapArea( #設定圖片範圍左下方
                                x = 0, 
                                y = imgheight *0.5,  
                                width = imgwidth *0.5, 
                                height = imgheight *0.5
                            )
                        ),
                        MessageImagemapAction(
                            text = '選擇' + ticket['body'][3]['name'],
                            area = ImagemapArea( #設定圖片範圍右下方
                                x = imgwidth *0.5, 
                                y = imgheight *0.5,  
                                width = imgwidth *0.5, 
                                height = imgheight *0.5
                            )
                        ),
                    ]
                ),
            ]
            line_bot_api.reply_message(event.reply_token,message)
        
        if mtext == '動物介紹' or mtext =='前一頁':
            items=[]
            animals = client.sendRequest("get/animals",{"start":0,"end":7},True)
            for i in range(7):
                items.append(QuickReplyButton(
                    action=PostbackAction(label=animals['body'][i], data=animals['body'][i]+'animal')
                ))
            items.append(QuickReplyButton(
                    action=MessageAction(label='更多', text='更多')
                ))
            message = TextSendMessage(
                text='請點選想要了解的動物~',
                quick_reply=QuickReply(
                items=items
                )
            )
            line_bot_api.reply_message(event.reply_token, message)   

        elif mtext == '更多':
            items=[]
            animals = client.sendRequest("get/animals",{"start":7,"end":14},True)
            for i in range(7):
                items.append(QuickReplyButton(
                    action=PostbackAction(label=animals['body'][i], data=animals['body'][i]+'animal')
                ))
            items.append(QuickReplyButton(
                    action=MessageAction(label='前一頁', text='前一頁')
                ))
            message = [
                TextSendMessage(
                text='請點選想要了解的動物~',
                quick_reply=QuickReply(
                    items=items
                    )
                ),
            ]
            line_bot_api.reply_message(event.reply_token, message)
        elif mtext == '選擇成人票' or mtext == '選擇兒童票' or mtext == '選擇幼兒票' or mtext == '選擇老人票':
            Confirm_template = TemplateSendMessage(
                alt_text='確認 template',
                template=ConfirmTemplate(
                    title='這是ConfirmTemplate',
                    text='請確定是否購買'+ mtext[-3:],
                    actions=[                              
                        PostbackTemplateAction(
                            label='確認',
                            text='確認',
                            data='action=buy_zoo'+ mtext[-3:]
                        ),
                        MessageTemplateAction(
                            label='取消',
                            text='取消'
                        )
                    ]
                )
            )
            line_bot_api.reply_message(event.reply_token,Confirm_template)
        elif mtext =='取消':
            message ='$您已取消目前的動作，若要重新選擇請點選選單~'
            line_bot_api.reply_message(event.reply_token,TextSendMessage(text=message,emojis=emoji_six))
        elif mtext == '額外票':
            columns=[]
            special_event = client.sendRequest("get/tickets",{"kind":"experience"},True)
            for i in range(3):
                columns.append(CarouselColumn(
                    thumbnail_image_url = client.loadImage(special_event['body'][i]['path']),
                    title = special_event['body'][i]['name'],
                    text = special_event['body'][i]['content'],
                    actions=[
                        DatetimePickerTemplateAction(
                            label = "選取參觀日期",
                            data = 'action=experience'+ special_event['body'][i]['name'],
                            mode = "date",
                            initial = "2023-06-03",
                            min = "2023-06-03",
                            max = "2024-06-03"
                        )
                    ]
                ))
            Carousel_template = TemplateSendMessage(
                alt_text='Carousel template',
                template=CarouselTemplate(columns=columns)
                )
            line_bot_api.reply_message(event.reply_token,Carousel_template)

        elif mtext == '票券':
            FlexMessages = json.load(open('tickets.json','r',encoding='utf-8'))
            t = client.sendRequest("get/tickets", {"kind":"user","id": event.source.user_id},True)
            if len(t['body'])<=0:
                message =[
                    TextSendMessage(text='$您尚未購買任何票券~點選下方選單可以進行購買!',emojis=emoji_seven),
                    TextSendMessage(text='$若您已購買票券但並未顯示，請來電通知，將有專人協助您~',emojis=emoji_eight),
                ]
                line_bot_api.reply_message(event.reply_token,message)
            else:
                for i in range(len(t['body'])):
                    FlexMessage = json.load(open('ticket.json','r',encoding='utf-8'))
                    FlexMessage['hero']['url'] = client.loadImage(t['body'][i]['tinfo']['path'])
                    if t['body'][i]['tinfo']['kind'] == 'ENTRANCE':
                        FlexMessage['body']['contents'][0]['text'] = '入園票'
                    else:
                        FlexMessage['body']['contents'][0]['text'] = t['body'][i]['tinfo']['name']
                    FlexMessage['body']['contents'][2]['contents'][0]['url'] = client.loadImage(t['body'][i]['tinfo']['qrcode'])
                    FlexMessage['body']['contents'][1]['contents'][0]['contents'][1]['text'] = t['body'][i]['date']
                    FlexMessage['body']['contents'][1]['contents'][1]['contents'][1]['text'] = t['body'][i]['tinfo']['type']
                    FlexMessages['contents'].append(FlexMessage)
                line_bot_api.reply_message(event.reply_token, FlexSendMessage('profile',FlexMessages))

        elif mtext == '導航':
            items = []
            items.append(QuickReplyButton(
                    action=MessageAction(label='入口', text='入口')
                ))
            message = [
                TextSendMessage(
                text='請點選起點位置',
                quick_reply=QuickReply(
                    items=items
                    )
                ),
            ]
            line_bot_api.reply_message(event.reply_token, message)

        elif mtext == '入口':
            items=[]
            zone = client.sendRequest("get/zones",None,True)
            for i in range(len(zone['body'])):
                items.append(QuickReplyButton(
                    action=PostbackAction(label= zone['body'][i], data = zone['body'][i] + 'zone')
                ))
            message = TextSendMessage(
                text='請點選想要前往的地點~',
                quick_reply=QuickReply(
                items=items
                )
            )
            line_bot_api.reply_message(event.reply_token, message)
        # elif mtext!= '':
        #     message =[
        #             TextSendMessage(text='$很抱歉~我們暫時沒有回應其他問題的功能喔',emojis=emoji_nine),
        #         ]
        #     line_bot_api.reply_message(event.reply_token,message)




    @handler.add(PostbackEvent)
    def handle_data(event):
        backdata=event.postback
        if 'buy_zoo' in backdata.data:
            message =  TemplateSendMessage(
                alt_text = "日期時間範例",
                template = ButtonsTemplate(
                thumbnail_image_url='https://tw.kobe-oukoku.com/wp-content/uploads/2017/04/17_kobeanimalkingdom_mob.jpg',
                    title='參觀日期',
                    text='請選擇',
                    actions=[
                        DatetimePickerTemplateAction(
                            label = "選取參觀日期",
                            data = "action=choose_time_zoo" + backdata.data.replace('action=buy_zoo',''),
                            mode = "date",
                            initial = "2023-06-03",
                            min = "2023-06-03",
                            max = "2024-06-03"
                        )
                    ]   
                )
            )
            line_bot_api.reply_message(event.reply_token,message)
        elif 'choose_time_zoo' in backdata.data:
            dt = backdata.params["date"]
            t = client.sendRequest("get/tickets_length", {"id": event.source.user_id},True)
            if t['body']>=12:
                message = TextSendMessage(text='很抱歉$您已達購票上限~',emojis=emoji_five)
            else:
                message =  TemplateSendMessage(
                    alt_text='確認購買',
                    template=ButtonsTemplate( 
                        thumbnail_image_url='https://tw.kobe-oukoku.com/wp-content/uploads/2017/04/17_kobeanimalkingdom_mob.jpg',
                        title='購買日期為'+ dt + backdata.data.replace('action=choose_time_zoo',''),
                        text='請選擇：',
                        actions=[
                            PostbackTemplateAction(
                                label='確定購買',
                                text='確定購買',
                                data='action=comfirm_zoo' + backdata.data.replace('action=choose_time_zoo','') +'/'+ dt,
                            ),
                            MessageTemplateAction(
                                label='重新選擇',
                                text='重新選擇'
                            ),
                            MessageTemplateAction(
                                label='取消',
                                text='取消'
                            ),
                        ]
                    )
                )
            line_bot_api.reply_message(event.reply_token,message)
        elif backdata.data.startswith('action=experience'):
            dt = backdata.params["date"]
            experience = client.sendRequest("get/tickets",{"kind":"experience","name":backdata.data.replace('action=experience','')},True)
            t = client.sendRequest("get/tickets_length", {"id": event.source.user_id},True) 
            if t['body']>=12:
                message = TextSendMessage(text='很抱歉$您已達購票上限~',emojis=emoji_five)
            else:
                message =  TemplateSendMessage(
                    alt_text='確認購買',
                    template=ButtonsTemplate( 
                        thumbnail_image_url = client.loadImage(experience['body']['path']),
                        title='購買日期為'+ dt + experience['body']['name']+'票券',
                        text='請選擇：',
                        actions=[
                            PostbackTemplateAction(
                                label='確定購買',
                                text='確定購買',
                                data='action=comfirm_experience'+ experience['body']['name'] +'/'+ dt
                            ),
                            MessageTemplateAction(
                                label='取消',
                                text='取消'
                            ),
                        ]
                    )
                )   
            line_bot_api.reply_message(event.reply_token,message)
        
        elif backdata.data.endswith('animal'):
            FlexMessage = json.load(open('animal.json','r',encoding='utf-8'))
            animal = client.sendRequest("get/animals",{"name":backdata.data.replace('animal','')},True)
            FlexMessage['contents'][0]['body']['contents'][0]['url'] = client.loadImage(animal['body']['path'])
            FlexMessage['contents'][0]['body']['contents'][1]['contents'][0]['contents'][0]['text'] = animal['body']['name']
            FlexMessage['contents'][0]['body']['contents'][1]['contents'][1]['contents'][0]['text'] = animal['body']['content']
            FlexMessage['contents'][0]['body']['contents'][2]['contents'][0]['text'] = animal['body']['habitat']
            print(client.loadImage(animal['body']['path']))
            line_bot_api.reply_message(event.reply_token, FlexSendMessage('profile',FlexMessage))

        elif backdata.data.endswith('zone'):
            zone = client.sendRequest("get/zones",{"name":backdata.data.replace('zone','')},True)
            message =  TemplateSendMessage(
                alt_text='確認',
                template=ButtonsTemplate(
                    thumbnail_image_url = client.loadImage(zone['body']['path']),
                    title='入口到'+ backdata.data.replace('zone','') + '的地圖導航',
                    text='請選擇：',
                    actions=[
                        PostbackTemplateAction(
                            label='確定',
                            text='確定',
                            data='action=goto'+ backdata.data.replace('zone','')
                        ),
                        MessageTemplateAction(
                            label='取消',
                            text='取消'
                        ),
                    ]
                )
            )
            line_bot_api.reply_message(event.reply_token,message)
        elif backdata.data.startswith('action=goto'):
            map = client.sendRequest("get/linemap",{"start":"入口","end":backdata.data.replace('action=goto','')},True)
            FlexMessage = json.load(open('map.json','r',encoding='utf-8'))
            FlexMessage['body']['contents'][1]['text'] = backdata.data.replace('action=goto','')
            FlexMessage['body']['contents'][3]['contents'][0]['url'] = client.loadImage(map['body']['path'])
            line_bot_api.reply_message(event.reply_token, FlexSendMessage('profile',FlexMessage))
        elif 'action=comfirm_' in backdata.data: 
            backdata.data = backdata.data.replace('action=comfirm_','')
            if backdata.data.startswith('zoo'):
                client.sendRequest("post/tickets",{"id":event.source.user_id,"tname":backdata.data.replace('zoo','').split('/')[0],"date":backdata.data.replace('zoo','').split('/')[1]},True)
            elif backdata.data.startswith('experience'):
                client.sendRequest("post/tickets",{"id":event.source.user_id,"tname":backdata.data.replace('experience','').split('/')[0],"date":backdata.data.replace('experience','').split('/')[1]},True)
            t = client.sendRequest("get/tickets", {"kind":"user","id": event.source.user_id},True)
            FlexMessage = json.load(open('ticket.json','r',encoding='utf-8'))
            FlexMessage['hero']['url'] = client.loadImage(t['body'][-1]['tinfo']['path'])
            if t['body'][-1]['tinfo']['kind'] == 'ENTRANCE':
                FlexMessage['body']['contents'][0]['text'] = '入園票'
            else:
                FlexMessage['body']['contents'][0]['text'] = t['body'][-1]['tinfo']['name']
            FlexMessage['body']['contents'][2]['contents'][0]['url'] = client.loadImage(t['body'][-1]['tinfo']['qrcode'])
            FlexMessage['body']['contents'][1]['contents'][0]['contents'][1]['text'] = t['body'][-1]['date']
            FlexMessage['body']['contents'][1]['contents'][1]['contents'][1]['text'] = t['body'][-1]['tinfo']['type']
            line_bot_api.reply_message(event.reply_token, FlexSendMessage('profile',FlexMessage))
    if __name__ == "__main__":
        app.run()
