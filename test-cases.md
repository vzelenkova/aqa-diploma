# 📋 Тест-кейсы для веб-приложения покупки тура

## 🔍 UI Тесты

| ID   | Название теста                                 | Предусловия                              | Шаги                                                                                   | Ожидаемый результат                                                | Приоритет |
|------|------------------------------------------------|------------------------------------------|----------------------------------------------------------------------------------------|--------------------------------------------------------------------|-----------|
| UI-1 | Успешная покупка тура по карте                 | Открыто приложение                       | 1. Нажать "Купить" <br> 2. Заполнить валидные данные карты <br> 3. Нажать "Продолжить" | Отображается уведомление об успешной оплате                        | High      |
| UI-2 | Отклонение покупки по недействительной карте   | Открыто приложение                       | То же, но карта "DECLINED"                                                             | Появляется ошибка "Ошибка! Банк отказал в проведении операции"     | High      |
| UI-3 | Покупка в кредит с одобренной картой           | Открыто приложение                       | Клик "Купить в кредит" → ввести данные карты "APPROVED" → отправить                    | Успешное подтверждение кредитной заявки                            | High      |
| UI-4 | Ошибки при незаполненных полях                 | Открыто приложение                       | Нажать "Купить", оставить поля пустыми → "Продолжить"                                  | Под каждым полем появляется сообщение об ошибке                    | Medium    |
| UI-5 | Ошибка при вводе недействительного месяца      | Открыто приложение                       | Ввести "13" в поле "Месяц"                                                             | Сообщение об ошибке "Неверный формат"                              | Medium    |
| UI-6 | Ошибка при вводе некорректного владельца карты | Открыто приложение                       | В поле "Владелец" ввести кириллицу или цифры                                           | Сообщение "Неверный формат" под полем "Владелец"                   | Medium    |

## 🔌 API Тесты

| ID    | Название теста                                | Метод | Endpoint             | Тело запроса                                                  | Ожидаемый ответ                       | Приоритет |
|-------|-----------------------------------------------|-------|----------------------|---------------------------------------------------------------|---------------------------------------|-----------|
| API-1 | Оплата тура по одобренной карте               | POST  | /api/v1/pay          | Данные карты "APPROVED"                                       | 200 OK, статус = "APPROVED"           | High      |
| API-2 | Оплата тура по отклоненной карте              | POST  | /api/v1/pay          | Данные карты "DECLINED"                                       | 200 OK, статус = "DECLINED"           | High      |
| API-3 | Кредит по одобренной карте                    | POST  | /api/v1/credit       | Данные карты "APPROVED"                                       | 200 OK, статус = "APPROVED"           | High      |
| API-4 | Ошибка при передаче пустого тела запроса      | POST  | /api/v1/pay          | {}                                                            | 400 Bad Request                       | Medium    |
| API-5 | Ошибка при передаче некорректного номера карты| POST  | /api/v1/credit       | Номер карты 0000000000000000                                  | 200 OK, статус = "DECLINED"           | Medium    |

## 🗄️ База данных

| ID     | Название теста                                | Описание                                                          | SQL-проверка                                                            | Ожидаемый результат                                     | Приоритет |
|--------|-----------------------------------------------|-------------------------------------------------------------------|-------------------------------------------------------------------------|---------------------------------------------------------|-----------|
| DB-1   | Проверка записи успешной покупки              | Совершить оплату картой "APPROVED"                                | SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;        | Статус: APPROVED                                        | High      |
| DB-2   | Проверка записи отклонённой покупки           | Совершить оплату картой "DECLINED"                                | SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;        | Статус: DECLINED                                        | High      |
| DB-3   | Проверка записи кредита по карте              | Покупка "в кредит" картой "APPROVED"                              | SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1; | Статус: APPROVED                                        | High      |
| DB-4   | Проверка связи между таблицами                | Сделать платёж/кредит                                             | Сравнить order_id с id из payment_entity или credit_request_entity      | Значения совпадают                                      | Medium    |

---

### ✅ Покрытие

- UI-тесты: форма оплаты, кредит, ошибки ввода
- API: оба способа оплаты, ошибки и валидации
- БД: корректность записей и связи
