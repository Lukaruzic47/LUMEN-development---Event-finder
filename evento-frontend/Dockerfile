FROM node:20-alpine as build

WORKDIR /app

COPY package*.json ./

# Install dependencies
RUN npm install

COPY . .

RUN npm run build

FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

# Start Nginx to serve the app
CMD ["nginx", "-g", "daemon off;"]
