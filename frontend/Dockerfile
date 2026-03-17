# syntax=docker/dockerfile:1

# -------- Build Stage --------
FROM --platform=$BUILDPLATFORM node:20-alpine AS build

WORKDIR /app

# copy dependency manifests first for caching
COPY bookstore-app/package*.json ./

RUN npm ci

# copy source
COPY bookstore-app .

# build production bundle
RUN npm run build


# -------- Runtime Stage --------
FROM nginx:1.27-alpine

# remove default nginx assets
RUN rm -rf /usr/share/nginx/html/*

# copy built frontend
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx","-g","daemon off;"]