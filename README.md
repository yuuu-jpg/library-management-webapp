# 図書館管理システム - Webアプリケーション

Java + Spring Boot + PostgreSQL で構築した図書館管理Webアプリケーションです。

## 🎯 プロジェクト概要

本プロジェクトは、架空の図書館を題材にした **Java + SQL による Webアプリケーションのポートフォリオ** です。  
書籍の検索・貸出状況の確認など、図書館の基本機能をブラウザ上で操作できます。

## 🛠️ 使用技術

### バックエンド
- **Java 17**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **Lombok**

### フロントエンド
- **HTML5 + CSS3**
- **Thymeleaf** (テンプレートエンジン)
- **Bootstrap 5** (CSSフレームワーク)

### データベース
- **PostgreSQL 15**

## 📁 プロジェクト構造

```
library-management-webapp/
├── pom.xml                          # Maven設定ファイル
├── schema.sql                       # テーブル定義
├── seed_data.sql                    # サンプルデータ
├── README.md                        # このファイル
│
└── src/
    └── main/
        ├── java/com/library/
        │   ├── LibraryApplication.java          # アプリ起動クラス
        │   ├── controller/
        │   │   ├── HomeController.java          # ホームページ
        │   │   ├── BookController.java          # 書籍管理
        │   │   ├── LoanController.java          # 貸出管理
        │   │   └── MemberController.java        # 会員管理
        │   ├── service/
        │   │   ├── BookService.java             # 書籍ビジネスロジック
        │   │   ├── LoanService.java             # 貸出ビジネスロジック
        │   │   └── MemberService.java           # 会員ビジネスロジック
        │   ├── repository/
        │   │   ├── BookRepository.java          # 書籍データアクセス
        │   │   ├── LoanRepository.java          # 貸出データアクセス
        │   │   ├── MemberRepository.java        # 会員データアクセス
        │   │   └── AuthorRepository.java        # 著者データアクセス
        │   └── model/
        │       ├── Book.java                    # 書籍エンティティ
        │       ├── Author.java                  # 著者エンティティ
        │       ├── Member.java                  # 会員エンティティ
        │       └── Loan.java                    # 貸出エンティティ
        │
        └── resources/
            ├── application.properties           # 設定ファイル
            ├── templates/
            │   ├── index.html                   # ホームページ
            │   ├── books/
            │   │   ├── list.html                # 書籍一覧・検索
            │   │   └── detail.html              # 書籍詳細
            │   ├── loans/
            │   │   └── list.html                # 貸出状況一覧
            │   └── members/
            │       └── list.html                # 会員一覧
            └── static/
                └── css/
                    └── style.css                # カスタムCSS
```

## 🚀 セットアップ手順

### 前提条件
- Java 17 以上
- Maven 3.6 以上
- PostgreSQL 15 以上

### Step 1: Java バージョン確認

```powershell
java -version
# Java 17以上であることを確認
```

### Step 2: PostgreSQL 準備

```powershell
# PostgreSQL にログイン（管理者ユーザー）
psql -U postgres

# データベース作成
CREATE DATABASE library_db;

# 接続切断
\q
```

### Step 3: テーブル作成とデータ投入

```powershell
# プロジェクトディレクトリに移動
cd c:\pleiades\2024-12\workspace\Display in Library Management

# テーブル作成
psql -U postgres -d library_db -f schema.sql

# サンプルデータ投入
psql -U postgres -d library_db -f seed_data.sql
```

### Step 4: Spring Boot アプリ起動

```powershell
# プロジェクト直下で実行
mvn spring-boot:run
```

アプリが起動したら、ブラウザで以下にアクセスしてください：

**http://localhost:8080**

## 📋 主な機能

### 1. 書籍検索・一覧画面 (`/books`)
- ジャンル別検索（小説 / SF / 技術書）
- 出版年別検索（全期間 / 2000年以降 / 2010年以降）
- 貸出可否の表示
- 書籍タイトルをクリックして詳細表示

### 2. 書籍詳細画面 (`/books/{id}`)
- 書籍の詳細情報表示
- 著者名の表示
- 貸出履歴の表示

### 3. 貸出状況一覧 (`/loans`)
- 現在貸出中の書籍一覧
- 返却期限と延滞判定
- 延滞情報の強調表示

### 4. 会員一覧 (`/members`)
- 会員情報の一覧表示
- 合計貸出回数の表示
- 入会日の表示

## 🗄️ データベース設計

### テーブル一覧

| テーブル | 説明 | 件数 |
|---------|------|------|
| authors | 著者情報 | 10 |
| books | 書籍情報 | 20 |
| book_authors | 書籍と著者の中間テーブル（多対多） | 25 |
| members | 会員情報 | 15 |
| loans | 貸出履歴 | 40 |

### キー SQLクエリ

**ジャンルと出版年で検索**
```sql
SELECT b.* FROM books b 
WHERE b.genre = :genre AND b.published_year >= :year 
ORDER BY b.title;
```

**現在貸出中の一覧**
```sql
SELECT l.* FROM loans l 
WHERE l.returned_at IS NULL 
ORDER BY l.due_date;
```

**会員ごとの貸出回数**
```sql
SELECT m.member_id, m.name, COUNT(l.loan_id) AS loan_count
FROM members m
LEFT JOIN loans l ON m.member_id = l.member_id
GROUP BY m.member_id, m.name
ORDER BY loan_count DESC;
```

## 🏗️ アーキテクチャ

本アプリケーションは 3層アーキテクチャを採用しています：

```
【Controller層】
  URLのルーティング、リクエスト/レスポンス処理
        ↓
【Service層】
  ビジネスロジック、データ整形
        ↓
【Repository層】
  SQLクエリ実行、データベースアクセス
        ↓
【Database】
  PostgreSQL
```

## 💻 動作確認

### ブラウザで以下にアクセス

| URL | 説明 |
|-----|------|
| http://localhost:8080 | ホームページ |
| http://localhost:8080/books | 書籍一覧・検索 |
| http://localhost:8080/books/1 | 書籍詳細（例：ID=1） |
| http://localhost:8080/loans | 貸出状況 |
| http://localhost:8080/members | 会員一覧 |

## 🔧 トラブルシューティング

### エラー: `Connection refused`
→ PostgreSQL がインストールされているか、実行中であるか確認してください

### エラー: `FATAL: password authentication failed`
→ `application.properties` のパスワードが正しいか確認してください

### エラー: `Database library_db does not exist`
→ Step 2, 3 の手順を再度実行してください

## 📝 注意事項

- `application.properties` にはデータベースパスワードが含まれています
- GitHub にアップロードする場合は、`.gitignore` に追加してください：
  ```
  src/main/resources/application.properties
  ```

## 🎓 ポートフォリオとしてのアピールポイント

✅ **Java スキル**
- Spring Boot フレームワークの理解と活用
- MVC パターンに基づいた設計
- Thymeleaf テンプレートエンジンの活用

✅ **SQL スキル**
- 複数テーブルの JOIN（INNER / LEFT）
- GROUP BY による集計
- CASE WHEN による条件分岐
- NULL 判定と論理演算

✅ **設計力**
- 既存リソースの再利用
- 一貫性のあるシステム設計
- 可読性と保守性を考慮したコード

## 📄 ライセンス

このプロジェクトはポートフォリオ用です。自由に改変・拡張できます。

## 👨‍💻 サポート

問題が発生した場合は、以下をご確認ください：
1. Java と Maven のバージョン
2. PostgreSQL のインストールと起動
3. スキーマとデータが正しく投入されているか

---

**作成日**: 2024年6月  
**更新日**: 2024年2月
