package util;

public class JedisClient {
private static JedisClient jedisClient;

    private JedisClient() {
    }

    private static JedisClient getInstance(){
        return (jedisClient == null) ? jedisClient = new JedisClient(): jedisClient;
    }
}
