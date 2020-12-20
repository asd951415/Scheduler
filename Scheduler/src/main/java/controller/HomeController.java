package controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.userdao;
import VO.UserVo;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	@RequestMapping(value = "/emplist", method = RequestMethod.GET)
	public String emplist( Model model) {
		List<UserVo> list = userdao.sel();
		model.addAttribute("list", list );
		
		logger.info("사용자가 emplist 조회요청을 하였습니다 {}", list);
		return "emplist";
	}
	
	@Scheduled(fixedDelay = 10000)
	public void scheduledEmplist() {
		List<UserVo> list = userdao.sel();
		logger.info("10초 마다 조회: {}", list);
		
	}
	
	//10분마다 실행
//		@Scheduled(cron = "0 0/10 * * * ?")
//		public void cron() {
//			List<UserVo> list = userdao.update();
//			logger.info("EmpUpdate 작업실행 업데이트 결과: {}, ");
//		}
//		
//		//1시간 주기로 실행
//		@Scheduled(cron = "0 0 0/1 * * *")
//		public void cron2() {
//			//userdao.delete();
//			logger.info("1시간 경과: 세션정보를 삭제합니다.", );
//		}
//		
//		//일 단위로 실행 
//		@Scheduled(cron = "0 30 0 * * *")
//		public void cron3() {
//			logger.info("매일 0시 30분에 가장 많은 조회수 게시물 선정");
//		}
	
}
