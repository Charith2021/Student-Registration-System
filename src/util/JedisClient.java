package util;

import redis.clients.jedis.Jedis;

public class JedisClient {
    private static JedisClient jedisClient;
    private final Jedis client;         //client is the singleton instance

    private JedisClient() {
        client = new Jedis("localhost", 9090);
        client.auth("123");
    }

    public static JedisClient getInstance() {
        return (jedisClient == null) ? jedisClient = new JedisClient() : jedisClient;
    }

    public Jedis getClient() {
        return client;
    }

}





//using the singleton design pattern

//me JedisClient ekak nowe. Me nikanma nikan class ekak. meyagen connection ekak haden na ne.
//Mata athatama sigleton karnna ona mama laga nathi Jedis kyana class eka