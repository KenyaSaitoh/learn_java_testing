CREATE TABLE PUBLISHER (
PUBLISHER_ID   INT AUTO_INCREMENT PRIMARY KEY, -- 出版社ID
PUBLISHER_NAME VARCHAR(30) NOT NULL            -- 出版社名
);

CREATE TABLE CATEGORY (
CATEGORY_ID   INT AUTO_INCREMENT PRIMARY KEY, -- カテゴリID
CATEGORY_NAME VARCHAR(20) NOT NULL            -- カテゴリ名
);

CREATE TABLE BOOK (
BOOK_ID      INT AUTO_INCREMENT PRIMARY KEY, -- 書籍ID
BOOK_NAME    VARCHAR(80) NOT NULL,           -- 書籍名
AUTHOR       VARCHAR(40) NOT NULL,           -- 著者
CATEGORY_ID  INT NOT NULL,                   -- カテゴリID
PUBLISHER_ID INT NOT NULL,                   -- 出版社ID
PRICE        INT NOT NULL,                   -- 価格
CONSTRAINT FK_CATEGORY_ID FOREIGN KEY(CATEGORY_ID)
    REFERENCES CATEGORY(CATEGORY_ID),
CONSTRAINT FK_PUBLISHER_ID FOREIGN KEY(PUBLISHER_ID)
    REFERENCES PUBLISHER(PUBLISHER_ID)
);

CREATE TABLE STOCK (
BOOK_ID      INT    PRIMARY KEY, -- 書籍ID
QUANTITY     INT    NOT NULL,    -- 在庫数
VERSION      BIGINT NOT NULL     -- バージョン番号
);

CREATE TABLE ORDER_TRAN (
ORDER_TRAN_ID    INT AUTO_INCREMENT PRIMARY KEY, -- 注文取引ID
ORDER_DATE       DATE NOT NULL,                  -- 注文日
CUSTOMER_ID      INT NOT NULL,                   -- 顧客ID
TOTAL_PRICE      INT NOT NULL,                   -- 注文金額合計
DELIVERY_PRICE   INT NOT NULL,                   -- 配送料金
DELIVERY_ADDRESS VARCHAR(30) NOT NULL,           -- 配送先住所
SETTLEMENT_TYPE  INT NOT NULL                   -- 決済方法
);

CREATE TABLE ORDER_DETAIL (
ORDER_TRAN_ID   INT NOT NULL, -- 注文取引ID
ORDER_DETAIL_ID INT NOT NULL, -- 注文明細ID
BOOK_ID         INT NOT NULL, -- 書籍ID
PRICE           INT NOT NULL, -- 価格
COUNT           INT NOT NULL, -- 注文数
CONSTRAINT PK_ORDER_DETAIL
    PRIMARY KEY(ORDER_TRAN_ID, ORDER_DETAIL_ID),
CONSTRAINT FK_ORDER_TRAN_ID FOREIGN KEY(ORDER_TRAN_ID)
    REFERENCES ORDER_TRAN(ORDER_TRAN_ID),
CONSTRAINT FK_BOOK_ID FOREIGN KEY(BOOK_ID)
    REFERENCES BOOK(BOOK_ID)
);