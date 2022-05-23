package homework.ClientApplication;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/client")
public class DepartmentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/person")
    public List<Object> getPersons() {
        String url = "http://localhost:5500/get/person";
        Object[] objects = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(objects);
    }

    @GetMapping("/get/friend")
    public List<Object> getFriendships() {
        String url = "http://localhost:5500/get/friend";
        Object[] objects = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(objects);
    }


    @GetMapping("/getPopular/friend")
    public List<Object> getPopular(@RequestParam(name = "popularPeople") int popularPeople) {
        String popularPeopleString = String.valueOf(popularPeople);
        String url = "http://localhost:5500/getPopular/friend?popularPeople=" + popularPeopleString;
        Object[] objects = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(objects);
    }

    @PostMapping("/add/person")
    public ResponseEntity<String> addPerson(@RequestParam(name = "name") String name) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String url = "http://localhost:5500/add/person?name=" + name;

        HttpEntity<String> request = new HttpEntity<String>(name, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response;
    }

    @PostMapping("/add/friend")
    public void addFriendship(@RequestParam(name = "id") long id, @RequestParam(name = "id1") long id1, @RequestParam(name = "id2") long id2) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "http://localhost:5500/add/friend?id=" + id + "&id1=" + id1 + "&id2=" + id2;

        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        params.put("id1", id1);
        params.put("id2", id2);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class, params);

    }

    @PutMapping("/update/person")
    public void updatePerson(@RequestParam(name = "id") long id, @RequestParam(name = "name") String name) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "http://localhost:5500/update/person?id=" + id + "&name=" + name;

        Map<Long, String> params = new HashMap<>();
        params.put(id, name);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class, params);
    }

    @DeleteMapping("/delete/person")
    public void deletePerson(@RequestParam(name = "id") long id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "http://localhost:5500/delete/person?id=" + id;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class, id);

    }
}
