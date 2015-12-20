package nz.co.robdog.core

import org.apache.http.HttpEntity
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.message.BasicNameValuePair

/**
 * Created by Rob on 20/12/2015.
 */
class BadDriverService {

    private HttpClient httpClient

    public String getForm() {
   		HttpPost httpPost = new HttpPost("https://${police.forms.host}/forms/online-community-roadwatch-report/9")
   		StringEntity entity = new StringEntity("".trim())
   		entity.setContentType('text/plain;charset=UTF-8')
   		httpPost.setEntity(entity)
   		CloseableHttpResponse response = executeRequest(httpPost)
   		assert response.statusLine.statusCode == 200
   		String payload = response.entity.content.text
   		EntityUtils.consumeQuietly(response.entity)
   		return payload
   	}

    /**
     * @param content a map of request parameters
     * @return a http entity instance that contains all parameters that need to be sent
     */
    protected HttpEntity createMessageBody(Map<String, String> content) {
        List<NameValuePair> pairs = content.collect { String key, String value ->
            return new BasicNameValuePair(key, value);
        }

        return new UrlEncodedFormEntity(pairs, "UTF-8")
    }

}
