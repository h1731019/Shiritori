package test;

import java.util.List;

import dao.ShiritoriDAO;

public class DaoTest {
	public static void main(String[] args) {
		ShiritoriDAO dao = new ShiritoriDAO();
		List<String> list = dao.randomFindByWord();
		for(String lists:list) {
			System.out.println(lists);
		}
	}

}
