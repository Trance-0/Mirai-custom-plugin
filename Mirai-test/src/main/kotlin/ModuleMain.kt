import customUtil.Data
import java.util.HashMap
import java.time.LocalDateTime
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.data.MessageChain

class ModuleMain {
    class Alarm{
        private var alarmPairs: HashMap<LocalDateTime,Data.Reminder> = hashMapOf()

        constructor(alarmPairs:Map<LocalDateTime,Data.Reminder>)

        suspend fun check(){
            for(i in this.alarmPairs.keys){
                if(i.isAfter(LocalDateTime.now())){
                    val newReminder= this.alarmPairs.get(i)
                    val interval= newReminder!!.interval
                    this.alarmPairs.remove(i);
                    this.alarmPairs.put(i.plusDays(interval),newReminder)
                    newReminder.target.sendMessage(newReminder.message)
                }
            }
        }
    }
}