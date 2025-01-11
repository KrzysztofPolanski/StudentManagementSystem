Student Management System

Opis projektu

Ten projekt to prosty system zarządzania studentami napisany w języku Java, który wykorzystuje bazę danych SQLite. Program umożliwia:

Dodawanie studentów do bazy danych.

Wyświetlanie listy studentów.

Usuwanie studentów z bazy danych.

Funkcjonalności

Dodawanie studentów

Użytkownik wprowadza ID, imię, wiek i ocenę studenta, a następnie dodaje go do bazy danych.

Wyświetlanie listy studentów

Program pobiera i wyświetla wszystkich studentów zapisanych w bazie danych.

Usuwanie studentów

Użytkownik podaje ID studenta, który ma zostać usunięty z bazy danych.

Struktura projektu

model — Zawiera klasę Student, reprezentującą studenta z polami ID, imię, wiek i ocena.

dao — Zawiera klasę StudentDAO, która obsługuje interakcję z bazą danych SQLite.

gui — Zawiera klasę GUI, która odpowiada za graficzny interfejs użytkownika.

main — Główna klasa uruchamiająca aplikację.

Wymagania

JDK 23 z obsługą funkcji preview.

SQLite.

Instrukcja uruchomienia

Upewnij się, że baza danych students.db jest w katalogu projektu.

Uruchom klasę GUI jako główną klasę aplikacji.