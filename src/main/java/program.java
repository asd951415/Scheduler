import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.userdao;
import VO.UserVo;

public class program {
	public static void main(String[] args) {
		
		userdao dao = new userdao();
		
		for(int i=0; i<dao.sel().size(); i++) {
			System.out.println(dao.sel().get(i).getName());
			System.out.println(dao.sel().get(i).getAge());
		}
		
		
	}
}
