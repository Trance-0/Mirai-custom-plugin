package customUtil

import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.data.MessageChain

class Data {
    class Source
    class Reminder(val target: Contact,val message: MessageChain,val interval:Long)
}