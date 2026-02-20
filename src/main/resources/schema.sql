-- =============================================
-- 図書館管理システム テーブル定義
-- =============================================

-- 既存テーブルがあれば削除（順番に注意）
DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS members;

-- ----------------------------
-- authors（著者）
-- ----------------------------
CREATE TABLE authors (
    author_id   SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    birth_date  DATE,
    bio         TEXT
);

-- ----------------------------
-- books（書籍）
-- ----------------------------
CREATE TABLE books (
    book_id        SERIAL PRIMARY KEY,
    title          VARCHAR(200) NOT NULL,
    genre          VARCHAR(50)  NOT NULL,
    published_year INTEGER,
    isbn           VARCHAR(20) UNIQUE
);

-- ----------------------------
-- book_authors（書籍と著者の中間テーブル）
-- ----------------------------
CREATE TABLE book_authors (
    book_id   INTEGER NOT NULL REFERENCES books(book_id),
    author_id INTEGER NOT NULL REFERENCES authors(author_id),
    PRIMARY KEY (book_id, author_id)
);

-- ----------------------------
-- members（会員）
-- ----------------------------
CREATE TABLE members (
    member_id  SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(200) NOT NULL UNIQUE,
    phone      VARCHAR(20),
    joined_at  DATE NOT NULL
);

-- ----------------------------
-- loans（貸出履歴）
-- returned_at が NULL = 現在貸出中
-- ----------------------------
CREATE TABLE loans (
    loan_id      SERIAL PRIMARY KEY,
    book_id      INTEGER NOT NULL REFERENCES books(book_id),
    member_id    INTEGER NOT NULL REFERENCES members(member_id),
    loaned_at    DATE NOT NULL,
    due_date     DATE NOT NULL,
    returned_at  DATE
);

-- ----------------------------
-- インデックス（検索を速くするため）
-- ----------------------------
CREATE INDEX idx_loans_book_id     ON loans(book_id);
CREATE INDEX idx_loans_member_id   ON loans(member_id);
CREATE INDEX idx_loans_returned_at ON loans(returned_at);
CREATE INDEX idx_books_genre       ON books(genre);
