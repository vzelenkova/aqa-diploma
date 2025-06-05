FROM node:18

# Создание рабочей директории внутри контейнера
WORKDIR /usr/src/app

# Копируем package.json и package-lock.json, если есть
COPY package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем всё остальное
COPY . .

# Открываем порт 9999
EXPOSE 9999

# Команда запуска
CMD ["npm", "start"]
