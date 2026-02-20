# library-management-webapp
Java + Spring Boot + PostgreSQL で構築した図書館管理システムのWebアプリケーション
# 📚 図書館管理システム

Java + Spring Boot + PostgreSQL で構築した図書館管理Webアプリケーションです。

## 概要

書籍の検索・貸出状況の確認・会員管理をブラウザ上で操作できます。
SQLポートフォリオとして作成したデータベース設計をもとに、
JavaのWebアプリケーションとして実装しました。

## 使用技術

| 技術 | 内容 |
|------|------|
| Java 17 | バックエンド |
| Spring Boot 3.x | Webアプリケーションフレームワーク |
| Spring Data JPA | DB接続・SQL実行 |
| Thymeleaf | HTMLテンプレートエンジン |
| PostgreSQL 18 | データベース |
| Bootstrap 5 | CSSフレームワーク |

## 機能一覧

- 📖 **書籍検索・一覧** — ジャンル・出版年・在庫状況で絞り込み検索
- 📋 **書籍詳細** — 著者情報・貸出履歴の確認
- 👤 **会員一覧** — 会員情報と合計貸出回数の表示
- 🔄 **貸出状況** — 現在貸出中・延滞の確認

## データベース設計

- 5テーブル構成（books / authors / book_authors / members / loans）
- 多対多リレーション（書籍と著者）を中間テーブルで表現
- `returned_at IS NULL` で貸出中を表現するシンプルな設計

## セットアップ

1. PostgreSQLで `library_db` を作成
2. `schema.sql` でテーブル作成
3. `seed_data.sql` でサンプルデータ投入
4. `application.properties` にDB接続情報を設定
5. F5（Spring Boot起動）
6. `http://localhost:8080` にアクセス
