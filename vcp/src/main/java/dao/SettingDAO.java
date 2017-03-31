package dao;

import entity.Setting;
import entity.User;

public interface SettingDAO {
	
	public int save(Setting setting);
	public Setting findSettingByUserId(User user);

}
