# RoomDBTool

Room是android用來儲存永續性資料的一個資料庫套件，相較於一開始常被人使用的SQLite，有許多的優勢：

1：將SQLite原本的實作封裝起來，使得開發者更方便進行資料庫存取，避免過度冗長的存取程式碼

2：編譯時期的sql語法檢查，降低執行時的錯誤率

3：語法結構簡單許多且易於整合


room的結構共有三個主體，分別是Entity、DAO、與Database，了解這三個結構操作後，就可以輕鬆的來使用room了！



本project是參考網路教學文章做成的簡易範例，用來測試room的功能，詳細操作步驟如下：

step 1：

在build.gradle中加入room套件

    dependencies {
    implementation "android.arch.persistence.room:runtime:1.0.0"
    annotationProcessor "android.arch.persistence.room:compiler:1.0.0"
    }


step 2：

建立Entity

這是用類別來操作資料庫表格的地方，會使用@Entity的標註，或是用tableName = xxx來表示，@ColumnInfo則是用來標示類別中的屬性，對應表格的欄位，如果有不想要連接的屬性，可以改用@Ignore來標示

例如我們建立一個person類別，當做是資料庫中的其中一個table的話，可以這樣表示

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/1.PNG)

step 3：

建立Dao

Dao就是Data Access Object的縮寫，顧名思義就是建立資料庫各種操作的方法，我們建立一個介面來定義資料庫的操作，最經典的就是CRUD(增刪改查)操作，分別使用不同的annotation來標示其功能，其中查詢的方法可接受一個SQL語法，所以假設我們的查詢動作是整個表單內容，可以這樣子寫

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/2.PNG)

step 4：

建立Room Database

這一步跟SQLite要建立instance的地方很像，差別在於，不用在這裡宣告冗長的create table語法了，因為已經被剛剛的entity取代，所以這裡要做的只剩下建立好database的實體，寫起來簡單許多，使用的是singleton方法來建來

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/3.PNG)

需要注意的地方是開頭的annotation，這裡下@Database表示這是一個database實體所在，並且有三個參數可以輸入，分別是entity所在地、是否保留版本記錄、以及版本號，未來有更新的話就跳號以示區別

step 5：

建立一個簡單的輸入畫面，並且insert為新增資料到db，query為取出資料表所有資料顯示在畫面上

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/4.PNG)


step 6：

執行新增動作。在輸入框輸入完資料之後按下insert按鈕，即呼叫剛才建立好的room instance來存下資料，注意反灰處即是room的操作是耗時工作，需要使用asynctask來完成，否則會新增失敗

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/5.PNG)

step 7：

新增成功之後，按下query按鈕，即會從room db中抓取資料，並且秀在畫面上，一樣注意此為耗時工作，需要使用asynctask來完成。並且顯示在畫面上的動作也必須改由runOnUiThread內執行，因為asynctask不是在main thread上，直接要求更新畫面會出錯

![image](https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/6.PNG)

最後來看一下整個操作流程吧！

<img src="https://github.com/LucaLin/RoomDBTool/blob/main/screenshots/sample.gif" width="320" height="600"/>

參考來源：

https://developer.android.com/training/data-storage/room/accessing-data

https://enginebai.com/2019/04/03/android-database-room/
