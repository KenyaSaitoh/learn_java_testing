CREATE TABLE CUSTOMER (
CUSTOMER_ID   INT AUTO_INCREMENT PRIMARY KEY, -- 顧客ID
CUSTOMER_NAME VARCHAR(30) NOT NULL,           -- 顧客名
PASSWORD      VARCHAR(60) NOT NULL,           -- パスワード
EMAIL         VARCHAR(30) NOT NULL,           -- Eメールアドレス
BIRTHDAY      DATE,                           -- 生年月日
ADDRESS       VARCHAR(120),                   -- 住所（UTF-8で40文字）
CONSTRAINT EMAIL_UNIQUE UNIQUE(EMAIL)
);