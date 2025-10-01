package com.nekozouneko.deathLogManager.wrapper

object Languages {
    object General {
        val PREFIX = "§8[§6DeathLog§8]"
        val ERROR_INVALID_RECEIVE_TYPE = "§cそのような受け取り方は存在しません。(each, near, self, simple)"
        val ERROR_DATABASE = "§cデータベースにエラーが発生しました。"
    }
    object Command {
        val ERROR_INVALID_SUBCOMMAND = "§cサブコマンドが存在しません。(help, set, info)"
        val ERROR_EXECUTE_ONLY_PLAYER = "§cこのコマンドはプレイヤーのみ実行できます。"
        val ERROR_NOT_ENOUGH_PERMISSION = "§cこのコマンドを実行する権限がありません。"
    }
    object HelpCommand{
        val HELP_MESSAGE = """
            ${General.PREFIX} §f- §eコマンドヘルプ
            §7・§f/deathlog help §8- §eコマンドヘルプを表示する
            §7・§f/deathlog help [each|near|self|simple] §8- §eそれぞれの受け取り方の詳細を見る
            §7・§f/deathlog info §8- §e現在の設定を確認する
            §7・§f/deathlog set [each|near|self|simple] §8- §e死亡ログの受け取り方を設定する
        """.trimIndent()

        object ReceiveHelps{
            val EACH = """
                ${General.PREFIX} §f- §eeachの説明
                §f§7自身が死んでしまった際や、誰かを倒した際の死亡ログのみ表示します。
            """.trimIndent()
            val NEAR = """
                ${General.PREFIX} §f- §enearの説明
                §f§7半径10ブロック以内で発生した死亡ログのみ表示します。
            """.trimIndent()
            val SELF = """
                ${General.PREFIX} §f- §eselfの説明
                §f§7自身の死亡ログのみ表示します。
            """.trimIndent()
            val SIMPLE = """
                ${General.PREFIX} §f- §esimpleの説明
                §f§7ツール名やツール詳細を除いたシンプルな死亡ログを表示します。
            """.trimIndent()
        }
    }
    object InfoCommand{
        fun CURRENT_TYPE(type: String) : String {
            return """
                ${General.PREFIX} §f- §eあなたの設定
                §f現在の受け取り方§7: §f${type} 
            """.trimIndent()
        }
    }
    object SetCommand{
        val ERROR_ENTER_RECEIVE_TYPE = "§c受け取り方を指定してください。(each, near, self, simple)"
        val ERROR_NOT_ENOUGH_PERMISSION = "§cこの受け取り方を選択する権限がありません。"
        fun SET_RECEIVE_TYPE(type: String) : String {
            return "${General.PREFIX} §a受け取り方を${type}に設定しました！"
        }
    }
}