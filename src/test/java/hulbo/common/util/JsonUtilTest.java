package hulbo.common.util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.LinkedHashMap;
import java.util.Map;


class JsonUtilTest {

    @Test
    void testToJson() throws JsonProcessingException {
        // Given: 테스트용 데이터 준비
        Map<String, Object> testData = new LinkedHashMap<>();
        testData.put("name", "Hulbo");
        testData.put("age", 25);
        testData.put("city", "Daejeon");

        // When: JSON 변환 수행
        String jsonResult = JsonUtil.toJson(testData);

        // Then: 예상된 JSON 형식인지 검증
        String expectedJson = "{\n" +
                "  \"age\" : 25,\n" +
                "  \"city\" : \"Daejeon\",\n" +
                "  \"name\" : \"Hulbo\"\n" +
                "}";

        // 두데이터 Json 형태로 변경
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> expectedMap = mapper.readValue(expectedJson, Map.class);
        Map<String, Object> resultMap = mapper.readValue(jsonResult, Map.class);
        Assertions.assertThat(resultMap).isEqualTo(expectedMap);
    }

    @Test
    void testToJsonWithNull() {
        // Given: null 입력
        Map<String, Object> testData = null;

        // When: JSON 변환 수행
        String jsonResult = JsonUtil.toJson(testData);

        // Then: 오류 메시지 검증
        Assertions.assertThat(jsonResult).contains("입력된 데이터가 null입니다");
    }
}
