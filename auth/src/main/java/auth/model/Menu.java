package auth.model;

import java.util.Date;
import java.util.List;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user_auth_menu
 */
public class Menu {

	private List<Menu> childrenMenus;
	
	/**
	 * Database Column Remarks: 主键 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * Database Column Remarks: 是否可用 0可用1不可用 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.enabled
	 * @mbg.generated
	 */
	private Integer enabled;
	/**
	 * Database Column Remarks: 菜单名称 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.name
	 * @mbg.generated
	 */
	private String name;
	/**
	 * Database Column Remarks: 上级菜单id 自关联 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.pid
	 * @mbg.generated
	 */
	private Integer pid;
	/**
	 * Database Column Remarks: 乐观锁 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.REVISION
	 * @mbg.generated
	 */
	private Integer revision;
	/**
	 * Database Column Remarks: 创建人 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.CREATED_BY
	 * @mbg.generated
	 */
	private String createdBy;
	/**
	 * Database Column Remarks: 创建时间 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.CREATED_TIME
	 * @mbg.generated
	 */
	private Date createdTime;
	/**
	 * Database Column Remarks: 更新人 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.UPDATED_BY
	 * @mbg.generated
	 */
	private String updatedBy;
	/**
	 * Database Column Remarks: 更新时间 This field was generated by MyBatis Generator. This field corresponds to the database column user_auth_menu.UPDATED_TIME
	 * @mbg.generated
	 */
	private Date updatedTime;

	public List<Menu> getChildrenMenus() {
		return childrenMenus;
	}

	public void setChildrenMenus(List<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.id
	 * @return  the value of user_auth_menu.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.id
	 * @param id  the value for user_auth_menu.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.enabled
	 * @return  the value of user_auth_menu.enabled
	 * @mbg.generated
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.enabled
	 * @param enabled  the value for user_auth_menu.enabled
	 * @mbg.generated
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.name
	 * @return  the value of user_auth_menu.name
	 * @mbg.generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.name
	 * @param name  the value for user_auth_menu.name
	 * @mbg.generated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.pid
	 * @return  the value of user_auth_menu.pid
	 * @mbg.generated
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.pid
	 * @param pid  the value for user_auth_menu.pid
	 * @mbg.generated
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.REVISION
	 * @return  the value of user_auth_menu.REVISION
	 * @mbg.generated
	 */
	public Integer getRevision() {
		return revision;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.REVISION
	 * @param revision  the value for user_auth_menu.REVISION
	 * @mbg.generated
	 */
	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.CREATED_BY
	 * @return  the value of user_auth_menu.CREATED_BY
	 * @mbg.generated
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.CREATED_BY
	 * @param createdBy  the value for user_auth_menu.CREATED_BY
	 * @mbg.generated
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? null : createdBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.CREATED_TIME
	 * @return  the value of user_auth_menu.CREATED_TIME
	 * @mbg.generated
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.CREATED_TIME
	 * @param createdTime  the value for user_auth_menu.CREATED_TIME
	 * @mbg.generated
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.UPDATED_BY
	 * @return  the value of user_auth_menu.UPDATED_BY
	 * @mbg.generated
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.UPDATED_BY
	 * @param updatedBy  the value for user_auth_menu.UPDATED_BY
	 * @mbg.generated
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy == null ? null : updatedBy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column user_auth_menu.UPDATED_TIME
	 * @return  the value of user_auth_menu.UPDATED_TIME
	 * @mbg.generated
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column user_auth_menu.UPDATED_TIME
	 * @param updatedTime  the value for user_auth_menu.UPDATED_TIME
	 * @mbg.generated
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}