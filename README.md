# DeathLogManager
死亡ログの受け取り方を任意に選択できるプラグイン
## コマンド
`/deathlog` `/dl` `/killlog` `/log`  
## 権限
すべて標準ではdefaultについています。
* コマンド `deathlog.use`
* 各種受け取り方の選択
  * `deathlog.switch.*`
    * `deathlog.switch.each`
    * `deathlog.switch.near`
    * `deathlog.switch.self`
    * `deathlog.switch.simple`
## 各種受け取り方
デフォルトの受け取り方はconfig.ymlから設定してください。
### each
自身が死んでしまった際や、誰かを倒した際の死亡ログのみ表示します。
### near
半径10ブロック以内で発生した死亡ログのみ表示します。
### self
自身の死亡ログのみ表示します。
### simple
ツール名やツール詳細を除いたシンプルな死亡ログを表示します。
## ビルド方法
APIバージョンによってビルドコマンドが異なります。
* 1.21 `mvn clean package -P 1.21`
* 1.20 `mvn clean package -P 1.20`