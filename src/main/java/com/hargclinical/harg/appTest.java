package com.hargclinical.harg;

import java.sql.Connection;

import DB.DB;

public class appTest {

	public static void main(String[] args) {

		Connection conn = DB.getConnection();
		DB.closeConnection();
	}
}
