package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import models.ShowtimeDao;

@Controller
@RequestMapping("/ticket")
public class TicketAjaxController {
	@Autowired
	ShowtimeDao showtimeDao;
	@Autowired
	Gson gson;
	
	
	@RequestMapping(path="/timeAjaxGet.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String timeAjaxHandle(@RequestParam String title) {
		List<Map> list = showtimeDao.getTimeByTitle(title);
		return gson.toJson(list);
	}
	
	@RequestMapping("/timeAjaxPost.do")
	@ResponseBody
	public String timeAjaxHandlePost(@RequestBody String body) {
		/*Gson gson = new Gson();
		Map map = gson.fromJson(body, Map.class);
			String title = (String)map.get("title");
		MovieDao movieDao  = new MovieDao();
		List<Integer> li = movieDao.getShowtimes(title);
		String json = gson.toJson(li);
		String json ="[";
		for(int i=0; i<li.size(); i++) {
			json += li.get(i);
			if(i !=li.size() -1)
				json +=",";
		}
		json +="]";
		return json;*/
		return "";
	}
}
