package play.aws;

import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;

public class MQTTClient {
    public static final String AWS_IOT_TOPIC = "control/lamp";
    public static final String AWS_IOT_ENDPOINT = "a2p4fyajwx9lux-ats.iot.us-east-1.amazonaws.com";
    public static final String AWS_IOT_CLIENT_ID = "iot-gateway";
    public static final String AWS_IOT_CERTIFICATE_FILE = "/Users/vsenger/Documents/minecraft-iot/8f2b2f776911332a0fea819064421830e592b55f32c4a6262918700841fc5c32-certificate.pem.crt";
    public static final String AWS_IOT_PRIVATE_KEY_FILE = "/Users/vsenger/Documents/minecraft-iot/8f2b2f776911332a0fea819064421830e592b55f32c4a6262918700841fc5c32-private.pem.key";
    private static final String AWS_IOT_INBOUND_TOPIC = "playground/sensors";
    private static final AWSIotQos AWS_IOT_INBOUND_TOPIC_QOS = AWSIotQos.QOS0;

    private static AWSIotMqttClient client = null;
    public static void main(String[] args) {
        init(AWS_IOT_ENDPOINT, AWS_IOT_CLIENT_ID, AWS_IOT_CERTIFICATE_FILE, AWS_IOT_PRIVATE_KEY_FILE);
        publish("control/lamp","0");
    }
    public static void init(String clientEndpoint, String clientId, String certificateFile, String privateKeyFile) {
        /*String clientEndpoint = AWS_IOT_ENDPOINT;
        String clientId = AWS_IOT_CLIENT_ID;
        String certificateFile = AWS_IOT_CERTIFICATE_FILE;
        String privateKeyFile = AWS_IOT_PRIVATE_KEY_FILE;*/

        SampleUtil.KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
        client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
        try {
            System.out.println("Iniciando conexão MQTT");
            client.connect(0, false);
            System.out.println("Cliente MQTT Conectado");
            //AWSIotTopic topic = new TopicListener(AWS_IOT_INBOUND_TOPIC, AWS_IOT_INBOUND_TOPIC_QOS, currentServer);
            //client.subscribe(topic, true);
        } catch (Exception e) {
            System.out.println("Erro ao inicializar conexão MQTT");
            e.printStackTrace();
            //LOGGER.error("Error connecting to IoT", e);
        }
    }
    public static void publish(String topic, String payload) {
        AWSIotQos qos = AWSIotQos.QOS0;
        long timeout = 3000;                    // milliseconds
        System.out.println("publicando mensagem " + topic + "payload " + payload);
        SimpleMessage message = new SimpleMessage(topic, qos, payload);
        try {
            client.publish(message);
            System.out.println("mensagem publicada" + topic + "payload " + payload);

        } catch (Exception e) {
            System.out.println("Erro enviando MSG MQTT");
            e.printStackTrace();
            //LOGGER.error("Error publishing event to IoT", e);
        }
    }
    //blocking mode...
    public static void publish1(String topic, String message) {
        try {
            //client.publish(ExampleMod.AWS_IOT_TOPIC, "{ \"msg\": \"direto do minecraft\", \"action\": \"block-break\"}");
            System.out.println("Enviando mensagem para " + topic + ": " + message);
            client.publish(topic, message);
            System.out.println("Mensagem enviada com sucesso");

        } catch (Exception e) {
            System.out.println("Erro enviando MSG MQTT");
            e.printStackTrace();
            //LOGGER.error("Error publishing event to IoT", e);
        }
    }
}
