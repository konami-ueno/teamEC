package com.internousdev.yellow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.yellow.dto.UserInfoDTO;
import com.internousdev.yellow.util.DBConnector;

public class UserInfoDAO
{
	public int createUser(String familyName, String firstName, String familyNameKana, String firstNameKana, String sex, String email, String loginId, String password)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		int count = 0;

		//	SQL作成
		String sql="INSERT INTO user_info(user_id, password, family_name, first_name, family_name_kana, first_name_kana,sex, email, status, logined, regist_date, update_date) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, familyName);
			preparedStatement.setString(4, firstName);
			preparedStatement.setString(5, familyNameKana);
			preparedStatement.setString(6, firstNameKana);
			preparedStatement.setString(7, sex);
			preparedStatement.setString(8, email);
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, 1);
			count = preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return count;
	}

	public boolean isExistsUserInfo(String loginId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		boolean result = false;

		//	SQLを作成
		String sql = "SELECT COUNT(*) AS count FROM user_info WHERE user_id = ?";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				result = (resultSet.getInt("count") > 0);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return result;
	}

	public UserInfoDTO getUserInfo(String loginId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		UserInfoDTO userInfoDTO = new UserInfoDTO();

		//	SQLを作成
		String sql="SELECT * FROM user_info WHERE user_id = ?";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				userInfoDTO.setId(resultSet.getInt("id"));
				userInfoDTO.setUserId(resultSet.getString("user_id"));
				userInfoDTO.setPassword(resultSet.getString("password"));
				userInfoDTO.setFamilyName(resultSet.getString("family_name"));
				userInfoDTO.setFirstName(resultSet.getString("first_name"));
				userInfoDTO.setFamilyNameKana(resultSet.getString("family_name_kana"));
				userInfoDTO.setFirstNameKana(resultSet.getString("first_name_kana"));
				userInfoDTO.setSex(resultSet.getInt("sex"));
				userInfoDTO.setEmail(resultSet.getString("email"));
				userInfoDTO.setStatus(resultSet.getString("status"));
				userInfoDTO.setLogined(resultSet.getInt("logined"));
				userInfoDTO.setRegistDate(resultSet.getDate("regist_date"));
				userInfoDTO.setUpdateDate(resultSet.getDate("update_date"));
			}
			else
			{
				userInfoDTO=null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return userInfoDTO;
	}

	public boolean checkPassword(String loginId, String password)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		boolean result = false;

		//	SQLを作成
		String sql = "SELECT COUNT(*) AS count FROM user_info WHERE user_id = ? AND password = ?";

		//	SQL実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				result = (resultSet.getInt("count") > 0);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return result;
	}

	public int resetPassword(String loginId, String password)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		//	SQLを作成
		String sql = "UPDATE user_info SET password = ? WHERE user_id = ?";

		int result= 0;

		//	SQLを実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, loginId);
			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return result;
	}

	public int login(String loginId, String password)
	{
		DBConnector dbConnector=new DBConnector();
		Connection connection = dbConnector.getConnection();

		int result=0;

		//	SQLを作成
		String sql = "UPDATE user_info SET logined = 1 WHERE user_id = ? AND password = ?";

		//	SQLを実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, password);

			result = preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}

		return result;
	}

	public int logout(String loginId)
	{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();

		int result=0;

		//	SQLを作成
		String sql = "UPDATE user_info SET logined = 0 WHERE user_id = ?";

		//	SQLを実行
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			result=preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
        finally
        {
            // Close
            try
            {
            	connection.close();
            }
            catch (SQLException e)
            {
            	e.printStackTrace();
            }
		}
		return result;
	}
}
