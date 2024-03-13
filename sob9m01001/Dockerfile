FROM hub.multiverse.io/library/java20:1.0.0

RUN mkdir -p /data/app

COPY  ./target/sob9m01001 /data/app


RUN chmod +x /data/app/*.sh
WORKDIR /data/app

ENTRYPOINT ["sh" , "start.sh"]
