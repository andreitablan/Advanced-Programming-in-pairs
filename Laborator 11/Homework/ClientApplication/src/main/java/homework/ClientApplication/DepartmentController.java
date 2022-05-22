package homework.ClientApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/client")
public class DepartmentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/person")
    public List<Object> getPersons(){
        String url = "http://localhost:5500/get/person";
        Object[] objects=restTemplate.getForObject(url,Object[].class);

        return Arrays.asList(objects);
    }

    @GetMapping("/get/friend")
    public List<Object> getFriendships(){
        String url = "http://localhost:5500/get/friend";
        Object[] objects=restTemplate.getForObject(url,Object[].class);

        return Arrays.asList(objects);
    }


    @GetMapping("/getPopular/friend")
    public List<Object> getPopular(@RequestParam(name="popularPeople")int popularPeople){
        String popularPeopleString= String.valueOf(popularPeople);
        String url = "http://localhost:5500/getPopular/friend?popularPeople=" + popularPeopleString;
        Object[] objects=restTemplate.getForObject(url,Object[].class);

        return Arrays.asList(objects);
    }

    /*@PostMapping("/add/person")
    public void addPerson (@RequestParam(name="name") String name){
        String url="http://localhost:5500/add/person?name=" + name;
        restTemplate.postForObject(url, HttpMethod.POST,String.class);
    }*/

}
