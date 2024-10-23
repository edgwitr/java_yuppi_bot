# yuppi-bot

## How to build

### 必要なもの
- コンパイラ  
[JDK23](https://www.oracle.com/in/java/technologies/downloads/)
- ビルダー  
[Gradle](https://gradle.org/)

### ビルド
```
gradle build
```

### 実行
```
gradle run
```

## How to use
### 環境変数
`DISCORD_TOKEN`と`TEST_SERVER_ID`が必要です。
dotenvを入れてあるので、プロジェクトルートに.envを設置するか、自身の環境変数に設定してください。
- DISCORD_TOKEN  
botのトークンです。
[デベロッパーページ](https://discord.com/developers/)からbotを追加して取得します。
追加や取得の方法は、[こちら](https://qiita.com/1ntegrale9/items/cb285053f2fa5d0cccdf)を参照してください。
- TEST_SERVER_ID
テストに使用するサーバーのIDです。
以下の手順で取得できます。
  1. User Settingsをクリック
  1. Advancedのセクションに移動
  1. Developer Modeをオンにする
  1. サーバアイコンを右クリックする
  1. Copy Server IDをクリックする

### botを追加した場合
BotタブのPrivileged Gateway Intentsで挙動に許可をする必要があります。
Presence Intent, Message Content Intentが必要です。

サーバへ招待する場合は、OAuth2タブのOAuth2 URL Generatorからbotスコープを選択し、
Administrator権限を選択したあと最下部のURLにアクセスし、招待したいサーバを選択します。
