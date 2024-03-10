-- 社員テーブル
CREATE TABLE EMPLOYEE (
EMPLOYEE_ID     INT NOT NULL,         -- 社員ID
EMPLOYEE_NAME   VARCHAR(30) NOT NULL, -- 社員名
DEPARTMENT_NAME VARCHAR(30),          -- 部署名
ENTRANCE_DATE   DATE NOT NULL,        -- 入社年月日
JOB_NAME        VARCHAR(30),          -- 役職名
SALARY          INT,                  -- 月給
PRIMARY KEY(EMPLOYEE_ID)
);