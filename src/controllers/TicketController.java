package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import models.MovieDao;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	MovieDao mdao;
	
	@GetMapping("/index.do")
	public String indexHandle(ModelMap modelMap) {
		//String[] data = "협상,명당,서치,상류사회".split(",");
		//modelMap.put("movies", data);
		List<Map> list = mdao.getMovetitle();
		
		String[] data2=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			String s= (String)map.get("TITLE");
			data2[i]=s;
			System.out.println("mv >"+data2[i]);
		}
		modelMap.put("movies", data2);
		return "index";
	}
	
	@GetMapping("/seat.do")
	public String indexHandle(@RequestParam Map param, WebRequest req) {
		System.out.println(param+".."+req);
		if(req.getAttribute("auth", WebRequest.SCOPE_SESSION) == null) {
			req.setAttribute("reserve", param, WebRequest.SCOPE_SESSION);
			return "redirect:/ticket/auth.do";
		}else {
			return "seat";	
		}
	}
	
	@GetMapping("/auth.do")
	public String authHandle() {
		return "auth";
	}
	
	@PostMapping("/auth.do")
	public String authHandle(@RequestParam Map param, HttpSession session, Map map) {
		session.setAttribute("user", param);
		session.setAttribute("auth", true);
		
		Map reserve = (Map)session.getAttribute("reserve");
		map.putAll(reserve);
		
		return "redirect:/ticket/seat.do";
	}
	
	@RequestMapping("/order.do")
	public String orderHandle(@RequestParam String[] seat, WebRequest webRequest) {
		Map user = (Map)webRequest.getAttribute("user", WebRequest.SCOPE_SESSION);
		Map reserve = (Map)webRequest.getAttribute("reserve", WebRequest.SCOPE_SESSION);
		System.out.println("user = " + user);
		System.out.println("reserve = " + reserve);
		for(int i=0; i<seat.length; i++) {
			System.out.println("seat = "+ seat[i]);
		}
		return "";
	}
	
}




