import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.ConsoleCommandSender
import net.mamoe.mirai.console.command.UserCommandSender
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.Image

class CommandMain {
    object FunctionCommand : CompositeCommand(
        PluginMain, "function", // "function" 是主指令名
        description = "示例指令"
        // prefixOptional = true // 还有更多参数可填, 此处忽略
    ) {

        // [参数智能解析]
        //
        // 在控制台执行 "/function <群号>.<群员> <持续时间>",
        // 或在聊天群内发送 "/function <@一个群员> <持续时间>",
        // 或在聊天群内发送 "/function <目标群员的群名> <持续时间>",
        // 或在聊天群内发送 "/function <目标群员的账号> <持续时间>"
        // 时调用这个函数
        @SubCommand // 表示这是一个子指令，使用函数名作为子指令名称
        suspend fun CommandSender.mute(target: Member, duration: Int) { // 通过 /function mute <target> <duration> 调用
            sendMessage("/function mute 被调用了, 参数为: $target, $duration")

            val result = kotlin.runCatching {
                target.mute(duration).toString()
            }.getOrElse {
                it.stackTraceToString()
            } // 失败时返回堆栈信息

            sendMessage("结果: $result")
        }

        @SubCommand
        suspend fun ConsoleCommandSender.foo() {
            // 使用 ConsoleCommandSender 作为接收者，表示指令只能由控制台执行。
            // 当用户尝试在聊天环境执行时将会收到错误提示。
        }

        @SubCommand("list", "查看列表") // 可以设置多个子指令名。此时函数名会被忽略。
        suspend fun CommandSender.ignoredFunctionName() { // 执行 "/function list" 时调用这个函数
            sendMessage("/function list 被调用了")
        }

        // 支持 Image 类型, 需在聊天中执行此指令.
        @SubCommand
        suspend fun UserCommandSender.test(image: Image) { // 执行 "/function test <一张图片>" 时调用这个函数
            // 由于 Image 类型消息只可能在聊天环境，可以直接使用 UserCommandSender。

            sendMessage("/function image 被调用了, 图片是 ${image.imageId}")
        }
    }

}