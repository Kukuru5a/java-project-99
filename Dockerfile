FROM gradle:8.3-jdk20

WORKDIR /app

COPY . .

RUN gradle installDist

CMD ./build/install/app/bin/app

EXPOSE 8080
