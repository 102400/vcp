package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Setting {
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="default_album")
	private int defaultAlbum;
	
	@Column(name="default_follower_category")
	private int defaultFollowerCategory;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDefaultAlbum() {
		return defaultAlbum;
	}

	public void setDefaultAlbum(int defaultAlbum) {
		this.defaultAlbum = defaultAlbum;
	}

	public int getDefaultFollowerCategory() {
		return defaultFollowerCategory;
	}

	public void setDefaultFollowerCategory(int defaultFollowerCategory) {
		this.defaultFollowerCategory = defaultFollowerCategory;
	}

}
