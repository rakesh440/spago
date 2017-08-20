package it.eng.spagobi.tools.datasource.bo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Sample {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		String object="[{\"customerId\":\"1\",\"customerName\": \"anand\" 	}, {\"customerId\": \"2\",\"customerName\": \"shiva\" 	}, {\"customerId\": \"3\",\"customerName\": \"kiran\" 	}, {\"customerId\": \"4\",\"customerName\": \"kavya\" 	}, {\"customerId\": \"5\",\"customerName\": \"vijay\" 	}] ";

	//	String object="{\"data\":[{\"customerId\":\"1\",\"customerName\": \"anand\" 	}, {\"customerId\": \"2\",\"customerName\": \"shiva\" 	}, {\"customerId\": \"3\",\"customerName\": \"kiran\" 	}, {\"customerId\": \"4\",\"customerName\": \"kavya\" 	}, {\"customerId\": \"5\",\"customerName\": \"vijay\" 	}] }";
		ObjectNode df = (ObjectNode) mapper.readValue(object, JsonNode.class);
		System.out.println(df);
	}

}
