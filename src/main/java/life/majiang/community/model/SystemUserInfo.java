package life.majiang.community.model;


import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * ClassName:SystemUserInfo
 * Function: 用户信息
 * Date:     2017-1-15 上午10:26:22
 * @author   尹有仁
 */
@Entity
@Table(name = "system_user_info")
public class SystemUserInfo extends Model {
    @Id
    private Integer id; //编号
    private String userName;    //用户名、唯一
    private String userPwd;    //加密密码
    private String appId;    //应用ID
    private String subAppid;    //子应用编码
    private Integer roleType;    //用户的角色类型，附加字段，子业务系统识别
    private Integer orgId;    //
    private String orgNo;    //组织机构编号
    private String realName;    //真实姓名
    private String phone;    //联系电话
    private Integer isRemove;    //是否删除
    private Integer isOrgAdmin;    //是否机构管理员    0：否 1:是
    private Integer isEnabled;    //是否启用
    private Date createdTime;    //创建时间
    private String creator;    //创建人
    private String attrField;    //附加字段
    private Date modifyTime;    //修改时间
    private String modifyBy;    //修改人
    private Integer isSysAdmin;    //是否系统管理员    0：否 1:是

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsRemove() {
        return isRemove;
    }

    public void setIsRemove(Integer isRemove) {
        this.isRemove = isRemove;
    }

    public Integer getIsOrgAdmin() {
        return isOrgAdmin;
    }

    public void setIsOrgAdmin(Integer isOrgAdmin) {
        this.isOrgAdmin = isOrgAdmin;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAttrField() {
        return attrField;
    }

    public void setAttrField(String attrField) {
        this.attrField = attrField;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Integer getIsSysAdmin() {
        return isSysAdmin;
    }

    public void setIsSysAdmin(Integer isSysAdmin) {
        this.isSysAdmin = isSysAdmin;
    }
}
