package customUtil

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value
import java.time.LocalDateTime

class Save {
    object ReminderData : AutoSavePluginConfig("Reminder") { // 文件名为 MyData, 会被保存为 MyData.yml
        var Alarms: Map<String,List<List<String>>> by value()
    }
    class Reader{
    fun readAlarm(alarm : Map<LocalDateTime,Data.Reminder>){
        var AlarmFlow=ReminderData.Alarms.toMutableMap()
            for(i in AlarmFlow){
                val time=LocalDateTime.parse(i.key)
                val data=i.value
                var messageChain =
                    alarm.
            }

    }
    }
    class Writer{

    }
}