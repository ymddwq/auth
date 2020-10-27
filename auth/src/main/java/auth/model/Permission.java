package auth.model;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_auth_permission
 */
public class Permission {

	/**
	 * Database Column Remarks: 主键 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * Database Column Remarks: 是否可用 0可用1不可用 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.enabled
	 * @mbg.generated
	 */
	private Integer enabled;
	/**
	 * Database Column Remarks: 权限名称 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.name
	 * @mbg.generated
	 */
	private String name;
	/**
	 * Database Column Remarks: 权限url This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.url
	 * @mbg.generated
	 */
	private String url;
	/**
	 * Database Column Remarks: 所属菜单 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.menuId
	 * @mbg.generated
	 */
	private Integer menuid;
	/**
	 * Database Column Remarks: 乐观锁 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.REVISION
	 * @mbg.generated
	 */
	private Integer revision;
	/**
	 * Database Column Remarks: 创建人 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.CREATED_BY
	 * @mbg.generated
	 */
	private String createdBy;
	/**
	 * Database Column Remarks: 创建时间 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.CREATED_TIME
	 * @mbg.generated
	 */
	private Date createdTime;
	/**
	 * Database Column Remarks: 更新人 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.UPDATED_BY
	 * @mbg.generated
	 */
	private String updatedBy;
	/**
	 * Database Column Remarks: 更新时间 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_permission.UPDATED_TIME
	 * @mbg.generated
	 */
	private Date updatedTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.id
	 * @return  the value of user_auth_permission.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.id
	 * @param id  the value for user_auth_permission.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.enabled
	 * @return  the value of user_auth_permission.enabled
	 * @mbg.generated
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.enabled
	 * @param enabled  the value for user_auth_permission.enabled
	 * @mbg.generated
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.name
	 * @return  the value of user_auth_permission.name
	 * @mbg.generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.name
	 * @param name  the value for user_auth_permission.name
	 * @mbg.generated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.url
	 * @return  the value of user_auth_permission.url
	 * @mbg.generated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.url
	 * @param url  the value for user_auth_permission.url
	 * @mbg.generated
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.menuId
	 * @return  the value of user_auth_permission.menuId
	 * @mbg.generated
	 */
	public Integer getMenuid() {
		return menuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.menuId
	 * @param menuid  the value for user_auth_permission.menuId
	 * @mbg.generated
	 */
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.REVISION
	 * @return  the value of user_auth_permission.REVISION
	 * @mbg.generated
	 */
	public Integer getRevision() {
		return revision;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.REVISION
	 * @param revision  the value for user_auth_permission.REVISION
	 * @mbg.generated
	 */
	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.CREATED_BY
	 * @return  the value of user_auth_permission.CREATED_BY
	 * @mbg.generated
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.CREATED_BY
	 * @param createdBy  the value for user_auth_permission.CREATED_BY
	 * @mbg.generated
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.CREATED_TIME
	 * @return  the value of user_auth_permission.CREATED_TIME
	 * @mbg.generated
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.CREATED_TIME
	 * @param createdTime  the value for user_auth_permission.CREATED_TIME
	 * @mbg.generated
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.UPDATED_BY
	 * @return  the value of user_auth_permission.UPDATED_BY
	 * @mbg.generated
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.UPDATED_BY
	 * @param updatedBy  the value for user_auth_permission.UPDATED_BY
	 * @mbg.generated
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_permission.UPDATED_TIME
	 * @return  the value of user_auth_permission.UPDATED_TIME
	 * @mbg.generated
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_permission.UPDATED_TIME
	 * @param updatedTime  the value for user_auth_permission.UPDATED_TIME
	 * @mbg.generated
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}