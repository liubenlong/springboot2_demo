package jsontest;

import com.example.pojo.Stu;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;


@JsonComponent
public class FooJsonComponent {

    public static class Serializer extends JsonSerializer<Stu> {
        @Override
        public void serialize(Stu value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeString("name=" + value.getName() + ",age=" + value.getAge());
        }

    }

    public static class Deserializer extends JsonDeserializer<Stu> {

        @Override
        public Stu deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonToken t = p.getCurrentToken();

            if (t == JsonToken.VALUE_STRING) {
                String trim = p.getText().trim();

                String[] split = trim.split(",");
                Stu stu = new Stu();
                stu.setName(split[0].split("=")[1]);
                stu.setAge(Integer.parseInt(split[1].split("=")[1]));
                return stu;
            }

            return (Stu) ctxt.handleUnexpectedToken(handledType(), p);

        }

    }

}
